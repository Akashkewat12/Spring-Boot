package com.exampleJPA_Mappig_w_3.A.JPA_Mapping.entitis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
//@Data
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="departments")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "department_manager")
    private EmployeeEntity manager;

    @ManyToMany(mappedBy = "freelanceDepartment")
    private Set<EmployeeEntity> freelancers;


    @OneToMany(mappedBy = "workerDepartment", fetch = FetchType.LAZY)
    private Set<EmployeeEntity> workers;

//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof DepartmentEntity that)) return false;
//        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getTitle());
//    }
}
