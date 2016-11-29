package com.danny.a1342097.assign3;

import com.danny.a1342097.assign3.Models.Note;
import com.danny.a1342097.assign3.Models.User;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the JSON representation of the user and note repositories.
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class TestJson {

    // Standard JSON for a user.
    private static final String userJson = "{\n" +
            "  \"name\" : \"ian\",\n" +
            "  \"password\" : \"8843d7f92416211de9ebb963ff4ce28125932878\",\n" +
            "  \"email\" : \"ian.clement@johnabbott.qc.ca\",\n" +
            "  \"_links\" : {\n" +
            "    \"self\" : {\n" +
            "      \"href\" : \"http://localhost:9999/user/1\"\n" +
            "    },\n" +
            "    \"user\" : {\n" +
            "      \"href\" : \"http://localhost:9999/user/1\"\n" +
            "    },\n" +
            "    \"created\" : {\n" +
            "      \"href\" : \"http://localhost:9999/user/1/created\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    // JSON represenation with null values
    private static final String userJsonNull = "{\n" +
            "  \"name\" : \"ian\",\n" +
            "  \"password\" : \"8843d7f92416211de9ebb963ff4ce28125932878\",\n" +
            "  \"email\" : null,\n" +
            "  \"_links\" : {\n" +
            "    \"self\" : {\n" +
            "      \"href\" : \"http://localhost:9999/user/1\"\n" +
            "    },\n" +
            "    \"user\" : {\n" +
            "      \"href\" : \"http://localhost:9999/user/1\"\n" +
            "    },\n" +
            "    \"created\" : {\n" +
            "      \"href\" : \"http://localhost:9999/user/1/created\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    // JSON representaiton of a list of users
    private static final String userListJson = "{\n" +
            "  \"_embedded\" : {\n" +
            "    \"user\" : [ {\n" +
            "      \"name\" : \"ian\",\n" +
            "      \"password\" : \"8843d7f92416211de9ebb963ff4ce28125932878\",\n" +
            "      \"email\" : \"ian.clement@johnabbott.qc.ca\",\n" +
            "      \"_links\" : {\n" +
            "        \"self\" : {\n" +
            "          \"href\" : \"http://localhost:9999/user/1\"\n" +
            "        },\n" +
            "        \"user\" : {\n" +
            "          \"href\" : \"http://localhost:9999/user/1\"\n" +
            "        },\n" +
            "        \"created\" : {\n" +
            "          \"href\" : \"http://localhost:9999/user/1/created\"\n" +
            "        }\n" +
            "      }\n" +
            "    }, {\n" +
            "      \"name\" : \"bar\",\n" +
            "      \"password\" : \"a9993e364706816aba3e25717850c26c9cd0d89d\",\n" +
            "      \"email\" : null,\n" +
            "      \"_links\" : {\n" +
            "        \"self\" : {\n" +
            "          \"href\" : \"http://localhost:9999/user/2\"\n" +
            "        },\n" +
            "        \"user\" : {\n" +
            "          \"href\" : \"http://localhost:9999/user/2\"\n" +
            "        },\n" +
            "        \"created\" : {\n" +
            "          \"href\" : \"http://localhost:9999/user/2/created\"\n" +
            "        }\n" +
            "      }\n" +
            "    } ]\n" +
            "  },\n" +
            "  \"_links\" : {\n" +
            "    \"self\" : {\n" +
            "      \"href\" : \"http://localhost:9999/user/\"\n" +
            "    },\n" +
            "    \"profile\" : {\n" +
            "      \"href\" : \"http://localhost:9999/profile/user\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    // Standard JSON representation of a note
    private static final String noteJson = "{\n" +
            "  \"created\" : \"2016-09-10T04:23:34.000+0000\",\n" +
            "  \"reminder\" : \"2016-10-12T20:32:54.000+0000\",\n" +
            "  \"title\" : \"Lorem ipsum dolor\",\n" +
            "  \"body\" : \"Lorem ipsum dolor sit amet, vel ei graece primis ullamcorper, unum denique an nam. Eum fabulas impedit tibique ex. No nonumes lobortis usu, te probo partem consequat vel. An dicta fastidii iracundia mel, eum pertinax consequat id. Est commune sadipscing ex, vocent laoreet an ius.\",\n" +
            "  \"category\" : -737419,\n" +
            "  \"_links\" : {\n" +
            "    \"self\" : {\n" +
            "      \"href\" : \"http://localhost:9999/note/1\"\n" +
            "    },\n" +
            "    \"note\" : {\n" +
            "      \"href\" : \"http://localhost:9999/note/1\"\n" +
            "    },\n" +
            "    \"createdBy\" : {\n" +
            "      \"href\" : \"http://localhost:9999/note/1/createdBy\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    // JSON representation with nulls
    private static final String noteJsonNull = "{\n" +
            "  \"created\" : \"2016-09-10T04:23:34.000+0000\",\n" +
            "  \"reminder\" : null,\n" +
            "  \"title\" : \"Lorem ipsum dolor\",\n" +
            "  \"body\" : null,\n" +
            "  \"category\" : -737419,\n" +
            "  \"_links\" : {\n" +
            "    \"self\" : {\n" +
            "      \"href\" : \"http://localhost:9999/note/1\"\n" +
            "    },\n" +
            "    \"note\" : {\n" +
            "      \"href\" : \"http://localhost:9999/note/1\"\n" +
            "    },\n" +
            "    \"createdBy\" : {\n" +
            "      \"href\" : \"http://localhost:9999/note/1/createdBy\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    // JSON representation of a list of notes
    private static final String noteListJson = "{\n" +
            "  \"_embedded\" : {\n" +
            "    \"note\" : [ {\n" +
            "      \"created\" : \"2016-09-10T04:23:34.000+0000\",\n" +
            "      \"reminder\" : \"2016-10-12T20:32:54.000+0000\",\n" +
            "      \"title\" : \"Lorem ipsum dolor\",\n" +
            "      \"body\" : \"Lorem ipsum dolor sit amet, vel ei graece primis ullamcorper, unum denique an nam. Eum fabulas impedit tibique ex. No nonumes lobortis usu, te probo partem consequat vel. An dicta fastidii iracundia mel, eum pertinax consequat id. Est commune sadipscing ex, vocent laoreet an ius.\",\n" +
            "      \"category\" : -737419,\n" +
            "      \"_links\" : {\n" +
            "        \"self\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/1\"\n" +
            "        },\n" +
            "        \"note\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/1\"\n" +
            "        },\n" +
            "        \"createdBy\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/1/createdBy\"\n" +
            "        }\n" +
            "      }\n" +
            "    }, {\n" +
            "      \"created\" : \"2016-09-10T04:24:34.000+0000\",\n" +
            "      \"reminder\" : \"2016-10-11T01:32:43.000+0000\",\n" +
            "      \"title\" : \"Nullam disputando eam\",\n" +
            "      \"body\" : \"Nullam disputando eam at, ullamcorper conclusionemque sed ad. Sit urbanitas adolescens cu, elit saepe ei nam. Latine voluptua adipisci sed ei. Per eu nostro eruditi sanctus, ad duo eleifend mediocrem definiebas, usu cibo commodo euripidis id.\",\n" +
            "      \"category\" : -10036753,\n" +
            "      \"_links\" : {\n" +
            "        \"self\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/2\"\n" +
            "        },\n" +
            "        \"note\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/2\"\n" +
            "        },\n" +
            "        \"createdBy\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/2/createdBy\"\n" +
            "        }\n" +
            "      }\n" +
            "    }, {\n" +
            "      \"created\" : \"2016-09-10T04:25:34.000+0000\",\n" +
            "      \"reminder\" : null,\n" +
            "      \"title\" : \"Pro civibus salutatus\",\n" +
            "      \"body\" : \"Pro civibus salutatus at, eum ei propriae accusamus, duo vidisse prompta ne. Has movet ocurreret elaboraret in, choro accommodare ne sea. Vel assum albucius nominati no. Te nam quem impetus, graeci intellegam mea ea.\",\n" +
            "      \"category\" : -10036753,\n" +
            "      \"_links\" : {\n" +
            "        \"self\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/3\"\n" +
            "        },\n" +
            "        \"note\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/3\"\n" +
            "        },\n" +
            "        \"createdBy\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/3/createdBy\"\n" +
            "        }\n" +
            "      }\n" +
            "    }, {\n" +
            "      \"created\" : \"2016-09-10T04:26:34.000+0000\",\n" +
            "      \"reminder\" : \"2016-10-13T04:12:12.000+0000\",\n" +
            "      \"title\" : \"An commodo legimus lucilius\",\n" +
            "      \"body\" : \"An commodo legimus lucilius cum, cu clita noluisse apeirian duo. Cu sanctus blandit splendide per. Duo no assum vidisse deleniti. Integre similique assueverit ne eum, ad mei admodum fuisset similique, zril saepe theophrastus vim ut. Ea tation omittam principes has. Id nec consequat adversarium, ne pri ipsum numquam.\",\n" +
            "      \"category\" : -448910,\n" +
            "      \"_links\" : {\n" +
            "        \"self\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/4\"\n" +
            "        },\n" +
            "        \"note\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/4\"\n" +
            "        },\n" +
            "        \"createdBy\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/4/createdBy\"\n" +
            "        }\n" +
            "      }\n" +
            "    }, {\n" +
            "      \"created\" : \"2016-09-10T04:27:34.000+0000\",\n" +
            "      \"reminder\" : \"2016-10-12T20:32:54.000+0000\",\n" +
            "      \"title\" : \"Te magna animal civibus\",\n" +
            "      \"body\" : \"Te magna animal civibus cum, assum efficiantur mel id. At nec meis oportere, nihil quidam temporibus mei ad. Nec suas convenire ea, ad qui numquam copiosae. Amet vide possit et has. Vim elitr maiorum voluptatibus te.\",\n" +
            "      \"category\" : -3381709,\n" +
            "      \"_links\" : {\n" +
            "        \"self\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/5\"\n" +
            "        },\n" +
            "        \"note\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/5\"\n" +
            "        },\n" +
            "        \"createdBy\" : {\n" +
            "          \"href\" : \"http://localhost:9999/note/5/createdBy\"\n" +
            "        }\n" +
            "      }\n" +
            "    } ]\n" +
            "  },\n" +
            "  \"_links\" : {\n" +
            "    \"self\" : {\n" +
            "      \"href\" : \"http://localhost:9999/note\"\n" +
            "    },\n" +
            "    \"profile\" : {\n" +
            "      \"href\" : \"http://localhost:9999/profile/note\"\n" +
            "    }\n" +
            "  }\n" +
            "}";


    /**
     * Test the parsing of a standard JSON representation
     */
    @Test
    public void testUserJson() {
        User user = User.parse(userJson);
        assertEquals("ian", user.getName());
        assertEquals("8843d7f92416211de9ebb963ff4ce28125932878", user.getPassword());
        assertEquals("ian.clement@johnabbott.qc.ca", user.getEmail());
    }

    /**
     * Test the parsing of JSON that includes nulls
     */
    @Test
    public void testUserJsonNullable() {
        User user = User.parse(userJsonNull);
        assertNull(user.getEmail());
    }

    /**
     * Test the user password checks works
     * - Uses SHA-1 algorithm to hash password argument
     */
    @Test
    public void testUserPassword() {
        User user = User.parse(userJson);
        assertTrue(user.isPassword("foobar"));
    }

    /**
     * Test that the links in the user JSON representation are stored.
     */
    @Test
    public void testUserLinks() {
        User user = User.parse(userJson);
        assertEquals("http://localhost:9999/user/1", user.getUrl());
    }

    /**
     * Test that the generated user is in the correct format for the server.
     */
    @Test
    public void testUserToJson() {
        User user = new User("bar", "abc", "bar@bar.com");
        assertEquals("{\"name\":\"bar\",\"password\":\"a9993e364706816aba3e25717850c26c9cd0d89d\",\"email\":\"bar@bar.com\"}", user.format());
    }

    /**
     * Test the parsing of a standard JSON representation: simple fields only
     */
    @Test
    public void testNoteJsonSimpleTypes() {
        Note note = Note.parse(noteJson);
        assertEquals("Lorem ipsum dolor", note.getTitle());
        assertEquals("Lorem ipsum dolor sit amet, vel ei graece primis ullamcorper, unum denique an nam. Eum fabulas impedit tibique ex. No nonumes lobortis usu, te probo partem consequat vel. An dicta fastidii iracundia mel, eum pertinax consequat id. Est commune sadipscing ex, vocent laoreet an ius.", note.getBody());
        assertEquals(-737419, note.getCategory());

    }

    /**
     * Test the parsing of a standard JSON representation: date fields only
     */
    @Test
    public void testNoteJsonDates() {
        Note note = Note.parse(noteJson);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            assertEquals(format.parse("2016-09-10T04:23:34.000+0000"), note.getCreated());
            assertEquals(format.parse("2016-10-12T20:32:54.000+0000"), note.getReminder());
        } catch (ParseException e) {
            fail();
        }
    }

    /**
     * Test that the links in the user JSON representation are stored.
     */
    @Test
    public void testNoteLinks() {
        Note note = Note.parse(noteJson);
        assertEquals("http://localhost:9999/note/1", note.getUrl());
        assertEquals("http://localhost:9999/note/1/createdBy", note.getCreatedBy());
    }

    /**
     * Test the parsing of JSON that includes nulls
     */
    @Test
    public void testNoteJsonNullable() {
        Note note = Note.parse(noteJsonNull);
        assertNull(note.getBody());
        assertNull(note.getReminder());
        assertFalse(note.HasReminder());
    }

    /**
     * Test that the generated note is in the correct format for the server.
     */
    @Test
    public void testNoteToJson() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            Note note = new Note();
            note.setTitle("foo");
            note.setBody("bar");
            note.setCategory(123);
            note.setReminder(format.parse("2016-09-10T04:23:34.000+0000"));
            note.setCreated(format.parse("2016-10-12T20:32:54.000+0000"));
            note.setCreatedBy("http://localhost:9999/user/1");
            assertEquals("{\"title\":\"foo\",\"body\":\"bar\",\"category\":123,\"reminder\":\"2016-09-10T00:23:34.000-0400\",\"created\":\"2016-10-12T16:32:54.000-0400\",\"createdBy\":\"http://localhost:9999/user/1\"}", note.format());
        } catch (ParseException e) {
            fail();
        }
    }

    /**
     * Test the parsing of the JSON representing a list of users
     */
    @Test
    public void testUserList() {
        User[] users = User.parseArray(userListJson);
        assertEquals(2, users.length);
        assertEquals("ian", users[0].getName());
        assertEquals("bar", users[1].getName());
    }

    /**
     * Test the parsing of a list of notes
     */
    @Test
    public void testNoteList() {
        Note[] notes = Note.parseArray(noteListJson);
        assertEquals(5, notes.length);
        assertEquals("Lorem ipsum dolor", notes[0].getTitle());
        assertEquals("Nullam disputando eam", notes[1].getTitle());
        assertEquals("Pro civibus salutatus", notes[2].getTitle());
        assertEquals("An commodo legimus lucilius", notes[3].getTitle());
        assertEquals("Te magna animal civibus", notes[4].getTitle());
    }

}





























