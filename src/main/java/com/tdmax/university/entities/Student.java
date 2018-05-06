package com.tdmax.university.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "student")
//@SqlResultSetMappings( {@SqlResultSetMapping(
//        name = "PrivilegedStudentsValueMapping",
//        classes = @ConstructorResult(
//                targetClass = Student.class,
//                columns = {
//                        @ColumnResult(name = "student_id", type = Long.class),
//                        @ColumnResult(name = "person_id", type = Person.class),
//                        @ColumnResult(name = "diploma_id", type = Long.class)})),
//        @SqlResultSetMapping(
//        name = "ContractStudentsValueMapping",
//        classes = @ConstructorResult(
//                targetClass = Student.class,
//                columns = {
//                        @ColumnResult(name = "student_id", type = Long.class),
//                        @ColumnResult(name = "person_id", type = Long.class),
//                        @ColumnResult(name = "diploma_id", type = Long.class)})),
//        @SqlResultSetMapping(
//                name = "HasDiplomaStudentsValueMapping",
//                classes = @ConstructorResult(
//                        targetClass = Student.class,
//                        columns = {
//                                @ColumnResult(name = "student_id", type = Long.class),
//                                @ColumnResult(name = "person_id", type = Long.class),
//                                @ColumnResult(name = "diploma_id", type = Long.class)}))})
public class Student {

    @Id
    @Column(name = "student_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
//    @JsonIgnore
    @JsonSerialize(as = Cafedra.class)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diploma_id", nullable = false)
//    @JsonIgnore
    @JsonSerialize(as = Cafedra.class)
    private Diploma diploma;

    public Student() {}

    public Student(Long id, Person person, Diploma diploma) {
        this.id = id;
        this.person = person;
        this.diploma = diploma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Diploma getDiploma() {
        return diploma;
    }

    public void setDiploma(Diploma diploma) {
        this.diploma = diploma;
    }
}
