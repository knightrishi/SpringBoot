package net.arnavsingh.journalApp.controller;

import net.arnavsingh.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries=new HashMap<>();

    @GetMapping
    //method inaside a controller should be public so that they can be accessed and invoked by
    // spring framework or external HTTP

    public List<JournalEntry> getAll() {
            return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        //@RequestBody measn telling spring to take data from req and turn it into a java obj that can be used in my code
    journalEntries.put(myEntry.getId(), myEntry);
    return true;


    }
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId) {
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {
        return journalEntries.remove(myId);
    }
    @PutMapping("id/{myId}")
    public JournalEntry updateEntryById(@PathVariable Long myId, @RequestBody JournalEntry myEntry) {
        return journalEntries.put(myId, myEntry);
    }
}
