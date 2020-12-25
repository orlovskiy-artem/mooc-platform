package com.orlovsky.mooc_platform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.net.URI;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "educational_steps")
public class EducationalStep implements Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column
    private URI eduMaterialUri;

    @Column
    private int position;

    @OneToMany(mappedBy = "passedEducationalStep",cascade = CascadeType.ALL)
    private List<StudentProgressItem> studentProgressItems;

    protected boolean canEqual(final Object other) {
        return other instanceof EducationalStep;
    }
    public String toString() {
        return "EducationalStep(id=" + this.getId() + ", courseId=" + this.getCourse().getId() + ", eduMaterialUri=" +
                this.getEduMaterialUri() + ", position=" + this.getPosition() ;
    }


}

