package net.arnavsingh.journalApp.controller;

import net.arnavsingh.journalApp.entity.JournalEntry;
import net.arnavsingh.journalApp.entity.User;
import net.arnavsingh.journalApp.services.JournalEntryService;
import net.arnavsingh.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
     private UserService userService;

    @GetMapping("{username}")
    public  ResponseEntity<?> getAllEntriesOfUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if(!all.isEmpty()){
        return new ResponseEntity<>(all,HttpStatus.OK);
        }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("{usename}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,String username) {
        try{

            myEntry.setDate(LocalDateTime.now());
            journalEntryService.save(myEntry,username);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
    journalEntryService.deleteById(myId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("id/{myId}")
//    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
////
////        JournalEntry old = journalEntryService.findById(myId).orElse(null);
////
////        if(old != null){
////        old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
////        old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent() );
////
////            journalEntryService.save(old, user);
////            return new ResponseEntity<>(old,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
}
