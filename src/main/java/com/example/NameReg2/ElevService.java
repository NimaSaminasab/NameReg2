package com.example.NameReg2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElevService {
    @Autowired
    ElevRepository elevRepository ;

    public Elev createElev(Elev elev) throws Exception {
        if(elev==null) {
            throw new Exception("Elev is null");
        }
        return elevRepository.save(elev) ;
    }
    public String deleteElev(Elev elev) throws Exception{
        if(elev==null){
            throw new Exception("Elev is null") ;
        }
        String name = elev.getName() ;
        elevRepository.delete(elev);
        return name + " is deleted" ;
    }
    public Elev findElevById(long id){
        return elevRepository.findById(id).orElse(null) ;
    }
    public List<Elev> findAllElev(){
        return(List<Elev>) elevRepository.findAll() ;
    }
    public Elev updateElev(Elev elev, long elevId){
        Elev oldElev = elevRepository.findById(elevId).orElse(null) ;
        if(oldElev!=null) {
            if (elev.getName() != null)
                oldElev.setName(elev.getName());
            if (elev.getTelephone() != null)
                oldElev.setTelephone(elev.getTelephone());
            elevRepository.save(oldElev);
            return oldElev;
        }
        else {
            return null ;
        }
    }
}
