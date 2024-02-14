package net.java.tms.Teller;

import net.java.tms.Utils.EntityResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tellers")
@CrossOrigin
public class TellerController {
    private final TellerService tellerService;
    public TellerController (TellerService tellerService){
        this.tellerService = tellerService;
    }
//    @Autowired
//    TellerService tellerService;

    @PostMapping("/add")
    public EntityResponse create(@RequestBody Teller teller) {
        return tellerService.create(teller);

    }
    @GetMapping("findById")
    public EntityResponse findById(@RequestParam Long id){
        return tellerService.findById(id);
    }
    @DeleteMapping("/delete")
    public EntityResponse delete(@RequestParam Long id){
        return tellerService.delete(id);
    }
    @PutMapping("/modify")
    public EntityResponse modify(@RequestBody Teller teller){
        return tellerService.modify(teller);
    }
    @PostMapping("/addBulk")
    public EntityResponse createBulk(@RequestBody List<Teller> tellers) {
        return tellerService.createBulk(tellers);
    }
    @GetMapping("/getAll")
    public EntityResponse getAllTellers() {
        return tellerService.getAll();
    }



//   @GetMapping("/findById")
//    public ResponseEntity<Optional<Teller>> getTellerById(@RequestParam Long id) {
//        Optional<Teller> teller = tellerService.getById(id);
//        if (teller != null) {
//            return ResponseEntity.ok(teller);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }