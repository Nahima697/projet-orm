package com.hb.cda.projetorm.repository.interfaces;

import com.hb.cda.projetorm.entity.Developper;
import com.hb.cda.projetorm.entity.Mastery;
import com.hb.cda.projetorm.entity.Techno;

import java.util.List;

public interface DevelopperRepository extends GenericRepository<Developper, Integer>{
   List<Developper> findByTechno(Techno techno);
   List<Developper> findByMasteryTechno(Techno techno, Mastery mastery);
}
