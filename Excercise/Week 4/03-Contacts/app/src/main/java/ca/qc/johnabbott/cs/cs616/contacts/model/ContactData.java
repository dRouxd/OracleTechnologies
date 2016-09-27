package ca.qc.johnabbott.cs.cs616.contacts.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data set of Contacts for ListView lecture and maybe more.
 *
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class ContactData {

    // the data, accessible by getData() but only as a copy.
    private static List<Contact> data;

    /* A 'static' block is used to initialize static fields.
     * It is used here to initialized more complex data.
     */
    static {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        data = new ArrayList<>();
        try {

            data.add(new Contact("Beaulah", "Muncy", "835-555-3256"));
            data.add(new Contact("Rikki", "Houghton", "717-555-3939").setLastCalled(format.parse("2016-02-28 15:38:22")).setCallCount(11));
            data.add(new Contact("Vina", "Marlowe", "760-555-5354").setLastCalled(format.parse("2015-07-29 13:00:13")).setCallCount(17));
            data.add(new Contact("Katharina", "Person", "226-555-7557").setLastCalled(format.parse("2016-06-18 08:31:10")).setCallCount(2));
            data.add(new Contact("Annalee", "Comstock", "959-555-6514").setLastCalled(format.parse("2016-06-03 16:36:32")).setCallCount(4));
            data.add(new Contact("Seema", "Valles", "440-555-8545").setLastCalled(format.parse("2016-03-04 20:40:49")).setCallCount(20));
            data.add(new Contact("Cami", "Lau", "211-555-6202").setLastCalled(format.parse("2016-01-28 01:00:05")).setCallCount(12));
            data.add(new Contact("Eleanora", "Cooks", "608-555-4243").setLastCalled(format.parse("2016-03-31 23:12:03")).setCallCount(13));
            data.add(new Contact("Tanja", "Chacon", "491-555-8012").setLastCalled(format.parse("2015-07-30 22:11:12")).setCallCount(20));
            data.add(new Contact("Queen", "Rhea", "440-555-2197").setLastCalled(format.parse("2016-03-26 04:12:00")).setCallCount(20));
            data.add(new Contact("Andree", "Bourgeois", "840-555-9012").setLastCalled(format.parse("2016-01-01 19:14:35")).setCallCount(20));
            data.add(new Contact("Celestina", "Lester", "237-555-3405").setLastCalled(format.parse("2016-04-25 00:29:33")).setCallCount(9));
            data.add(new Contact("Giselle", "Gilliam", "518-555-3725").setLastCalled(format.parse("2015-06-20 06:48:22")).setCallCount(19));
            data.add(new Contact("Keely", "Maas", "501-555-2643").setLastCalled(format.parse("2015-12-28 06:30:19")).setCallCount(10));
            data.add(new Contact("Misha", "Wiggins", "641-555-9506").setLastCalled(format.parse("2015-10-30 05:24:10")).setCallCount(5));
            data.add(new Contact("Launa", "Vernon", "452-555-8583").setLastCalled(format.parse("2015-06-15 20:02:30")).setCallCount(18));
            data.add(new Contact("Erasmo", "Sanford", "502-555-6239").setLastCalled(format.parse("2016-01-14 10:48:46")).setCallCount(1));
            data.add(new Contact("Anastacia", "Nowlin", "173-555-6488").setLastCalled(format.parse("2016-05-25 18:51:15")).setCallCount(1));
            data.add(new Contact("Emmitt", "Farrar", "327-555-9531").setLastCalled(format.parse("2016-08-06 02:29:58")).setCallCount(13));
            data.add(new Contact("Winfred", "Farias", "817-555-5628").setLastCalled(format.parse("2016-06-12 00:40:07")).setCallCount(11));
            data.add(new Contact("Deetta", "Thiel", "841-555-3525").setLastCalled(format.parse("2015-12-17 03:45:13")).setCallCount(3));
            data.add(new Contact("Scottie", "Orellana", "520-555-4934").setLastCalled(format.parse("2016-06-06 06:03:31")).setCallCount(3));
            data.add(new Contact("Remedios", "Devito", "102-555-2496").setLastCalled(format.parse("2015-12-12 06:19:33")).setCallCount(16));
            data.add(new Contact("Patricia", "Jasper", "213-555-3573").setLastCalled(format.parse("2016-04-19 12:57:33")).setCallCount(19));
            data.add(new Contact("Dottie", "Horn", "847-555-7654").setLastCalled(format.parse("2015-08-09 12:04:15")).setCallCount(1));
            data.add(new Contact("Madie", "Baughman", "259-555-1166").setLastCalled(format.parse("2015-08-02 11:16:55")).setCallCount(13));
            data.add(new Contact("Debby", "Crocker", "622-555-1342").setLastCalled(format.parse("2016-04-05 14:56:32")).setCallCount(15));
            data.add(new Contact("Grisel", "Bermudez", "541-555-9404").setLastCalled(format.parse("2015-10-14 17:32:30")).setCallCount(16));
            data.add(new Contact("Takako", "Wolff", "286-555-4787").setLastCalled(format.parse("2015-06-10 01:52:01")).setCallCount(16));
            data.add(new Contact("Alverta", "Mccool", "939-555-4852").setLastCalled(format.parse("2016-01-26 11:41:02")).setCallCount(20));
            data.add(new Contact("Eliz", "Robinette", "991-555-6487").setLastCalled(format.parse("2016-03-30 05:33:46")).setCallCount(6));
            data.add(new Contact("Dario", "Steffen", "189-555-4409").setLastCalled(format.parse("2016-05-28 10:45:28")).setCallCount(1));
            data.add(new Contact("Carma", "Winkler", "466-555-1630").setLastCalled(format.parse("2015-06-11 08:39:46")).setCallCount(4));
            data.add(new Contact("Dacia", "Castellano", "676-555-0100").setLastCalled(format.parse("2016-03-14 17:22:27")).setCallCount(19));
            data.add(new Contact("Porter", "Libby", "885-555-6487").setLastCalled(format.parse("2015-07-18 20:38:04")).setCallCount(6));
            data.add(new Contact("Deangelo", "Bottoms", "301-555-3638").setLastCalled(format.parse("2016-08-01 02:49:21")).setCallCount(10));
            data.add(new Contact("Angle", "Yi", "743-555-6262").setLastCalled(format.parse("2015-11-27 18:33:05")).setCallCount(16));
            data.add(new Contact("Aleta", "Baumgartner", "578-555-3264").setLastCalled(format.parse("2016-07-28 17:08:18")).setCallCount(9));
            data.add(new Contact("Olin", "Barrios", "394-555-0107").setLastCalled(format.parse("2015-08-13 15:15:20")).setCallCount(13));
            data.add(new Contact("Zelda", "Brenner", "899-555-0281").setLastCalled(format.parse("2016-07-11 04:17:40")).setCallCount(14));
            data.add(new Contact("Alvaro", "Cathey", "229-555-1600").setLastCalled(format.parse("2015-11-02 22:26:37")).setCallCount(2));
            data.add(new Contact("Bettye", "Cecil", "931-555-7167").setLastCalled(format.parse("2016-02-18 13:41:56")).setCallCount(5));
            data.add(new Contact("Amiee", "Diamond", "314-555-4909").setLastCalled(format.parse("2016-06-05 12:23:08")).setCallCount(18));
            data.add(new Contact("Ellie", "Weed", "540-555-8994").setLastCalled(format.parse("2016-07-25 04:11:49")).setCallCount(1));
            data.add(new Contact("Marjory", "Haight", "620-555-9100").setLastCalled(format.parse("2016-06-28 02:10:34")).setCallCount(5));
            data.add(new Contact("Isaura", "Posey", "448-555-6743").setLastCalled(format.parse("2015-11-19 00:22:47")).setCallCount(6));
            data.add(new Contact("Johnathon", "Carrion", "247-555-4172").setLastCalled(format.parse("2015-08-19 19:47:50")).setCallCount(17));
            data.add(new Contact("Syreeta", "Hawthorne", "689-555-4381").setLastCalled(format.parse("2016-07-19 02:47:55")).setCallCount(20));
            data.add(new Contact("Leopoldo", "Dugas", "891-555-9803").setLastCalled(format.parse("2016-05-18 06:35:17")).setCallCount(3));
            data.add(new Contact("Norine", "Cheatham", "836-555-7970").setLastCalled(format.parse("2016-09-02 01:45:17")).setCallCount(17));
            data.add(new Contact("Noble", "Shay", "236-555-8405").setLastCalled(format.parse("2016-06-26 01:34:06")).setCallCount(3));
            data.add(new Contact("Sharell", "Hastings", "792-555-2501").setLastCalled(format.parse("2016-02-28 05:56:21")).setCallCount(5));
            data.add(new Contact("Gearldine", "Curley", "159-555-6673").setLastCalled(format.parse("2016-03-27 17:24:05")).setCallCount(16));
            data.add(new Contact("Wyatt", "Womack", "228-555-4803").setLastCalled(format.parse("2016-06-07 11:33:40")).setCallCount(2));
            data.add(new Contact("Lucile", "Morse", "289-555-6599").setLastCalled(format.parse("2015-09-25 12:33:36")).setCallCount(3));
            data.add(new Contact("Honey", "Grey", "731-555-4905").setLastCalled(format.parse("2015-07-17 16:02:20")).setCallCount(5));
            data.add(new Contact("Lavera", "Bach", "893-555-8042").setLastCalled(format.parse("2015-11-11 12:23:59")).setCallCount(15));
            data.add(new Contact("Pa", "Woody", "196-555-6136").setLastCalled(format.parse("2016-03-01 18:20:27")).setCallCount(15));
            data.add(new Contact("Chrystal", "Nava", "628-555-5509").setLastCalled(format.parse("2016-02-07 01:50:17")).setCallCount(4));
            data.add(new Contact("Genesis", "Morrill", "503-555-0471").setLastCalled(format.parse("2015-09-01 07:41:10")).setCallCount(10));
            data.add(new Contact("Shayne", "Peyton", "975-555-1470").setLastCalled(format.parse("2015-11-20 03:08:28")).setCallCount(9));
            data.add(new Contact("Jaqueline", "Amador", "214-555-9234"));
            data.add(new Contact("Tama", "Mclean", "691-555-6008"));
            data.add(new Contact("Micki", "Dalton", "133-555-1736"));
            data.add(new Contact("Wynell", "Gates", "206-555-8198"));
            data.add(new Contact("Chiquita", "Kellogg", "567-555-8042"));
            data.add(new Contact("Janessa", "Heffner", "885-555-0072"));
            data.add(new Contact("Damaris", "Cone", "524-555-5652"));
            data.add(new Contact("Daryl", "Waldron", "967-555-5638"));
            data.add(new Contact("Veronika", "Currie", "388-555-0948"));
            data.add(new Contact("Freida", "Self", "386-555-6433"));
            data.add(new Contact("Mckenzie", "Schulte", "254-555-2083"));
            data.add(new Contact("Maurine", "Mattox", "514-555-0206"));
            data.add(new Contact("Rickie", "Lewandowski", "880-555-6370"));
            data.add(new Contact("Darcel", "Nelms", "158-555-8236"));
            data.add(new Contact("Buddy", "Hamrick", "444-555-8263"));
            data.add(new Contact("Cathi", "Durr", "775-555-6092"));
            data.add(new Contact("Terina", "Oaks", "710-555-0448"));
            data.add(new Contact("Lin", "Conyers", "742-555-8003"));
            data.add(new Contact("Manual", "Parris", "610-555-0396"));
            data.add(new Contact("Giuseppina", "Atwell", "151-555-0591"));
            data.add(new Contact("Michaele", "Lake", "348-555-4931"));
            data.add(new Contact("Toshiko", "Slagle", "631-555-6314"));
            data.add(new Contact("Jessi", "Sykes", "716-555-3244"));
            data.add(new Contact("Hana", "Mora", "932-555-7234"));
            data.add(new Contact("Leonora", "Cambell", "712-555-6705"));
            data.add(new Contact("Lawana", "Conrad", "358-555-6867"));
            data.add(new Contact("Madalene", "Jose", "501-555-1348"));
            data.add(new Contact("Graham", "Beers", "669-555-0631"));
            data.add(new Contact("Dudley", "Tam", "346-555-3058"));
            data.add(new Contact("Lauri", "Schaeffer", "178-555-2277"));
            data.add(new Contact("Kenisha", "Basham", "148-555-7051"));
            data.add(new Contact("Antonina", "Martindale", "158-555-3582"));
            data.add(new Contact("Bobby", "Benedict", "762-555-9223"));
            data.add(new Contact("Riley", "Runyan", "942-555-8104"));
            data.add(new Contact("Clemente", "Abrams", "798-555-5700"));
            data.add(new Contact("Delmar", "Caskey", "459-555-4418"));
            data.add(new Contact("Kayleigh", "Cahill", "278-555-5717"));
            data.add(new Contact("Kasey", "Tidwell", "660-555-7958"));
            data.add(new Contact("Son", "Deaton", "345-555-8319"));

        } catch (ParseException e) {
            // will not occur
            e.printStackTrace();
        }

        // deal with it.
        Collections.shuffle(data);
    }

    /**
     * Get the sample data. Each call gives a copy of the List, not the original unsorted list.
     * @return
     */
    public static List<Contact> getData() {

        // create a copy of the original data and return it.
        // copy prevent messing with order of the original.
        List<Contact> dataCopy = new ArrayList<>(data.size());
        for(Contact c : data)
            dataCopy.add(c);
        // this didn't work as expected Collections.copy(dataCopy, data);
        return dataCopy;
    }

    /**
     * Get the Contact by it's ID or null if it's not found
     * @param id
     * @return
     */
    public static Contact getContactById(long id) {
        for(Contact c : data)
            if(c.getId() == id)
                return c;
        return null;
    }

}
