package ru.itis.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "file")
public class FileEntity extends AbstractEntity{

    @Column(name = "storage_file_name", nullable = false, unique = true)
    private String storageFileName;

    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    private String description;

    @Column(nullable = false)
    private Long size;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;


}
