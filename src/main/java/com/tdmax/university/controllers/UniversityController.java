package com.tdmax.university.controllers;

import com.tdmax.university.entities.Cafedra;
import com.tdmax.university.entities.Speciality;
import com.tdmax.university.entities.Student;
import com.tdmax.university.repositories.CafedraRepository;
import com.tdmax.university.repositories.SpecialityRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping(path="/api")
public class UniversityController {

    @Autowired
    CafedraRepository cafedraRepository;

    @Autowired
    SpecialityRepository specialityRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(path="/cafedra")
    public @ResponseBody Iterable<Cafedra> cafedra(@RequestParam(value="id", required = false) Long cafedraId) {

        if (cafedraId != null) {
            List<Long> ids = new ArrayList<>();
            ids.add(cafedraId);
            return cafedraRepository.findAllById(ids);
        } else {
            return cafedraRepository.findAll();
        }
    }

    @RequestMapping(path="/speciality")
    public @ResponseBody Iterable<Speciality> speciality(@RequestParam(value="id", required = false) Long specialityId) {

        if (specialityId != null) {
            List<Long> ids = new ArrayList<>();
            ids.add(specialityId);
            return specialityRepository.findAllById(ids);
        } else {
            return specialityRepository.findAll();
        }
    }

    @RequestMapping(path="/privilegedStudents")
    public @ResponseBody Iterable<Student> privilegedStudents() {

        final String PRIVILEGED_STUDENTS = "SELECT student_id, name, surname, patronymic, sp.privilege_name FROM student s INNER JOIN person p on s.person_id = p.person_id INNER JOIN person_privilege pp on p.person_id = pp.person_id INNER JOIN sprivilege sp on pp.privilege_id = sp.privilege_id";

        Query q1 = entityManager.createNativeQuery(PRIVILEGED_STUDENTS);
        List<Student> privilegedStudentsList = q1.getResultList();
        Iterable<Student> iterable = privilegedStudentsList;

        return iterable;
    }

    @RequestMapping(path="/contractStudentsWithLastPayment")
    public @ResponseBody Iterable<Student> contractStudents() {

        final String CONTRACT_STUDENTS = "SELECT student_id, name, surname, payment_date, payment_num FROM (" +
                "SELECT s.student_id, name, surname, payment_date, payment_num, row_number() over(partition by s.student_id order by payment_date desc) as rn " +
                "from student s " +
                "INNER JOIN person p on s.person_id = p.person_id " +
                "INNER JOIN contract c on s.student_id = c.student_id " +
                "INNER JOIN scontract_kind ck on c.contract_kind_id = ck.contract_kind_id " +
                "INNER JOIN payment pay on c.contract_id = pay.contract_id " +
                "WHERE c.contract_kind_id = 1 ) T " +
                "WHERE rn = 1";

        Query q2 = entityManager.createNativeQuery(CONTRACT_STUDENTS);
        List<Student> contractStudentsList = q2.getResultList();
        Iterable<Student> iterable = contractStudentsList;

        return iterable;
    }

    @RequestMapping(path="/hasDiplomaStudentsAndAverageMark")
    public @ResponseBody Iterable<Student> hasDiplomaStudents() {

        final String HAS_DIPLOMA_STUDENTS = "SELECT s.student_id, name, surname, diploma_id, AVG(mark_value) as mark_value FROM student s " +
                "INNER JOIN person p on s.person_id = p.person_id " +
                "INNER JOIN student_marks sm on s.student_id = sm.student_id " +
                "INNER JOIN smark smk on sm.mark_id = smk.mark_id " +
                "WHERE diploma_id is not null " +
                "GROUP BY s.student_id, name, surname, diploma_id " +
                "HAVING AVG(mark_value) >= 90";

        Query q3 = entityManager.createNativeQuery(HAS_DIPLOMA_STUDENTS);
        List<Student> hasDiplomaStudentsList = q3.getResultList();
        Iterable<Student> iterable = hasDiplomaStudentsList;

        return iterable;
    }

}
