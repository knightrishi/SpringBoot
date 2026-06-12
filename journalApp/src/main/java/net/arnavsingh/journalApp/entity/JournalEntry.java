package net.arnavsingh.journalApp.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


//This is called a POJO class means "Plain Old Java Object"
@Document(collection = "journal_entries")

@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private LocalDateTime date;


    private String content;

}
