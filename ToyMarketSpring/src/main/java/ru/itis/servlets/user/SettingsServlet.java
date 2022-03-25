package ru.itis.servlets.user;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.FilesForm;
import ru.itis.dto.UpdatePasswordForm;
import ru.itis.services.user.SettingsServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/in/settings")
@MultipartConfig
public class SettingsServlet extends HttpServlet {

    SettingsServiceImpl settingsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.settingsService = springContext.getBean(SettingsServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/settings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String email = (String) request.getSession().getAttribute("email");
        if (action != null) {
            switch (action) {
                case "Фото":
                    Part filePart = request.getPart("file");
                    String name = filePart.getSubmittedFileName();
                    if (!name.isEmpty()) {

                        StringBuilder p= new StringBuilder();
                        String[] arrSplit = name.split("\\.");
                        for (int i=0; i < arrSplit.length-1; i++) {
                            p.append(arrSplit[i]+".");
                            System.out.println(p);
                        }
                        p.append("png");
                        name = String.valueOf(p);

                        FilesForm form = FilesForm.builder()
                                .fileName(name)
                                .fileMimeType(filePart.getContentType())
                                .fileSize((int) filePart.getSize())
                                .contentDisposition(request.getParameter("description"))
                                .build();

                        if (!settingsService.fileNameIsExist(name)) {
                            settingsService.upload(form, filePart.getInputStream());
                            settingsService.updatePhoto(email, name);
                            response.sendRedirect("/in/profile");
                        } else {
                            request.getRequestDispatcher("/WEB-INF/views/settings.jsp").forward(request, response);

                        }
                    } else {
                        request.getRequestDispatcher("/WEB-INF/views/settings.jsp").forward(request, response);

                    }
                    break;
                case "Пароль" :
                    UpdatePasswordForm form1 = UpdatePasswordForm.builder()
                            .oldPassword(request.getParameter("old_password"))
                            .newPassword(request.getParameter("new_password"))
                            .build();
                    if (settingsService.correctOldPassword(email, form1.getOldPassword()) &&
                    form1.getNewPassword().equals(request.getParameter("repeat_password"))){
                        settingsService.updatePassword(email, form1.getNewPassword());
                        response.sendRedirect("/signIn");
                    } else { doGet(request, response);}
                    break;
                case "Выйти" :
                    settingsService.dropCookie(response, "isAuthenticated");
                    request.getSession().removeAttribute("email");
                    response.sendRedirect("/signIn");
                default:
                    return;
            }
        }
    }
}
