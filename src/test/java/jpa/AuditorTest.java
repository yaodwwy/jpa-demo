package jpa;

import jpa.entity.Class;
import jpa.repo.ClassRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class AuditorTest {

    @Autowired
    ClassRepo classRepo;

    @Test
    public void auditor() throws InterruptedException {
        Class class3 = Class.builder().name("class3").build();
        class3 = classRepo.saveAndFlush(class3);
        log.debug("创建人: " + class3.getCreatedBy());
        log.debug("创建日期: " + class3.getDateCreated());
        log.debug("修改人: " + class3.getModifiedBy());
        log.debug("修改日期: " + class3.getDateModified());

        Thread.sleep(5000);

        Optional<Class> classRepoById = classRepo.findById(class3.getId());
        Class class5 = classRepoById.get();
        class5.setName("class5");
        classRepo.saveAndFlush(class5);
        log.debug("创建人: " + class5.getCreatedBy());
        log.debug("创建日期: " + class5.getDateCreated());
        log.debug("修改人: " + class5.getModifiedBy());
        log.debug("修改日期: " + class5.getDateModified());
    }
}