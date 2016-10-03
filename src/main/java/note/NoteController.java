package note;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NoteController {

    private ArrayList<Note> noteList = new ArrayList<Note>();

    @RequestMapping(value="/notes", method=RequestMethod.POST)
    public @ResponseBody Note createNote(@RequestBody Note newNote) {
        //System.out.println("Got a note:  " + newNote.getId() + "   " + newNote.getBody());
        long newId = noteList.size();

        newNote.setId(newId);

        if(noteList.add(newNote)) {
            return newNote;
        } else {
            return null;
        }
    }

    @RequestMapping(value="/notes/{id}", method=RequestMethod.GET)
    public @ResponseBody Note getNote(@PathVariable(value="id") long searchId) {
        //System.out.println("Request for note with id:  " + searchId);
        for(Note n : noteList) {
            if(n.getId() == searchId) {
                return n;
            }
        }

        return null;
    }

    @RequestMapping(value="/notes", method=RequestMethod.GET)
    public @ResponseBody ArrayList<Note> getNoteList(
           @RequestParam(value="query", required=false) String query) {

        if(query == null || query.isEmpty()) {
            return noteList;
        }

        ArrayList<Note> retVal = new ArrayList<Note>();

        for(Note n : noteList) {
            if(n.getBody().contains(query)) {
                retVal.add(n);
            }
        }

        return retVal;
    }
}
