package ca.qc.johnabbott.cs616.notes.server;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "note", path = "note")
public interface NoteRepository extends CrudRepository<Note, Long> {
}
