package com.example.NameReg2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ElevController {
    @Autowired
    ElevService elevService ;

    @CrossOrigin
    @PostMapping("/createElev")
    @ResponseBody
    public String createElev(@RequestBody Elev elev) throws Exception{
        if(elev!=null)
        {
            elevService.createElev(elev);
            return "ok" ;
        }
        return "error" ;
    }
    @CrossOrigin
    @DeleteMapping("/deleteElev/{id}")
    @ResponseBody
    public String deleteElevById(@PathVariable long id) throws Exception{
        if(id>0){
            Elev elev= elevService.findElevById(id) ;
            if(elev!=null){
                return elevService.deleteElev(elev) ;
            }
            else{
                return "Finner ikke elev med id " +id  ;
            }

        }
        return "id ikke gyldig" ;
    }

    @CrossOrigin
    @GetMapping("/findElevById/{id}")
    @ResponseBody
    public Elev findElevById(@PathVariable long id){
        if(id>0){
            return elevService.findElevById(id) ;
        }
        return null ;
    }

    @GetMapping("findAllElev")
    @ResponseBody
    public List<Elev> findAllElev(){
        return elevService.findAllElev() ;
    }
    @PutMapping("/updateElev/{elevId}")
    @ResponseBody
    public Elev updateElev(@RequestBody Elev elev, @PathVariable long elevId) throws Exception{
       return elevService.updateElev(elev,elevId) ;
    }
}
