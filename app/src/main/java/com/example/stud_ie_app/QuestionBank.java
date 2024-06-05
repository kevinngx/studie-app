package com.example.stud_ie_app;

import com.example.stud_ie_app.ApiClasses.OxfordApiHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class QuestionBank {

    String category;
    ArrayList<String> words;
    public static String[] categories = {
            "Transport",
            "Beach",
            "Circus",
            "Jobs",
            "Weather",
            "Nature",
            "Music",
            "Exercise",
            "Politics",
            "Astronomy"
    };

    public static int getScore(String category) {
        // Returns the score value of the questions
        switch (category) {
            case "Transport":
                return 100;
            case "Beach":
                return 150;
            case "Circus":
                return 200;
            case "Jobs":
                return 250;
            case "Weather":
                return 300;
            case "Nature":
                return 350;
            case "Music":
                return 400;
            case "Exercise":
                return 450;
            case "Politics":
                return 500;
            case "Astronomy":
                return 550;
        }
        return 0;
    }

    public static ArrayList<String> getOptionsFromWordBank(String category, String word) {
        // Returns a randomized list of words from a category, excluding the input word to be used as question options
        ArrayList<String> categoryBank = getWordsBank(category);
        Collections.shuffle(categoryBank);
        for (int i = 0; i < categoryBank.size(); i++) {
            if (categoryBank.get(i).equals(word)) {
                categoryBank.remove(i);
            }
        }
        return categoryBank;
    }

    public class Options {
        // Used to pull synonyms of the word to be used as options
        // NOTE: this was replaced by getOptionsFromWordBank as using the API slowed the app too much,
        // even when run asynchronously, so had to replace with existing word bank.
        ArrayList<String> options;
        public Options(String word) {
            ArrayList<String> synonyms;
            ArrayList<String> options = new ArrayList<>();
            try {
                synonyms = OxfordApiHelper.getSynonyms(word);
                options.add(word);
                for (int i = 0; i < 3; i++) {
                    options.add(synonyms.get(i));
                }
                Collections.shuffle(options);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public QuestionBank(String category) {
        this.category = category;
        words = getWordsBank(category);

    }

    public static ArrayList<String> getWordsBank(String category) {
        // Questionbank for the app
        ArrayList<String> wordBank = new ArrayList<>();
        switch (category) {
            case "Transport":
                wordBank.add("fly");
                wordBank.add("train");
                wordBank.add("car");
                wordBank.add("swim");
                wordBank.add("boat");
                wordBank.add("plane");
                wordBank.add("balloon");
                wordBank.add("canoe");
                wordBank.add("soar");
                wordBank.add("run");
                break;
            case "Beach":
                wordBank.add("dive");
                wordBank.add("fish");
                wordBank.add("lake");
                wordBank.add("ocean");
                wordBank.add("sail");
                wordBank.add("tide");
                wordBank.add("underwater");
                wordBank.add("wharf");
                wordBank.add("vacation");
                wordBank.add("pier");
                break;
            case "Circus":
                wordBank.add("clown");
                wordBank.add("costume");
                wordBank.add("festival");
                wordBank.add("parade");
                wordBank.add("magic");
                wordBank.add("entertainer");
                wordBank.add("cannon");
                wordBank.add("admission");
                wordBank.add("audience");
                wordBank.add("bicycle");
                break;
            case "Jobs":
                wordBank.add("butcher");
                wordBank.add("teacher");
                wordBank.add("lawyer");
                wordBank.add("scientist");
                wordBank.add("nurse");
                wordBank.add("engineer");
                wordBank.add("singer");
                wordBank.add("writer");
                wordBank.add("chef");
                wordBank.add("inventor");
                break;
            case "Weather":
                wordBank.add("sunny");
                wordBank.add("overcast");
                wordBank.add("rain");
                wordBank.add("chilly");
                wordBank.add("freezing");
                wordBank.add("downpour");
                wordBank.add("temperate");
                wordBank.add("smog");
                wordBank.add("frigid");
                wordBank.add("cloudy");
                break;
            case "Nature":
                wordBank.add("flower");
                wordBank.add("forest");
                wordBank.add("organic");
                wordBank.add("environment");
                wordBank.add("decomposition");
                wordBank.add("organism");
                wordBank.add("tropical");
                wordBank.add("farming");
                wordBank.add("climate");
                wordBank.add("valley");
                break;
            case "Music":
                wordBank.add("melody");
                wordBank.add("accompaniment");
                wordBank.add("baroque");
                wordBank.add("encore");
                wordBank.add("ensemble");
                wordBank.add("improvise");
                wordBank.add("medley");
                wordBank.add("recital");
                wordBank.add("tempo");
                wordBank.add("vibration");
                break;
            case "Exercise":
                wordBank.add("endurance");
                wordBank.add("active");
                wordBank.add("energy");
                wordBank.add("training");
                wordBank.add("performance");
                wordBank.add("perspiration");
                wordBank.add("therapeutic");
                wordBank.add("stretch");
                wordBank.add("movement");
                wordBank.add("exertion");
                break;
            case "Politics":
                wordBank.add("cabinet");
                wordBank.add("campaign");
                wordBank.add("candidate");
                wordBank.add("representation");
                wordBank.add("unprecedented");
                wordBank.add("delegate");
                wordBank.add("endorsement");
                wordBank.add("amendment");
                wordBank.add("conservative");
                wordBank.add("allegation");
                break;
            case "Astronomy":
                wordBank.add("illumination");
                wordBank.add("celestial");
                wordBank.add("measurement");
                wordBank.add("meteor");
                wordBank.add("gravity");
                wordBank.add("inertia");
                wordBank.add("spectrum");
                wordBank.add("spherical");
                wordBank.add("galaxy");
                wordBank.add("magnitude");
                break;
        }
        return wordBank;
    }

    public static ArrayList<String> getSentencesBank(String category) {
        // Sentence bank for the app
        ArrayList<String> sentenceList = new ArrayList<>();
        switch (category) {
            case "Transport":
                sentenceList.add("Mankind never knew pleasure until they discovered how to fly");
                sentenceList.add("The engines had twin overhead camshafts which were gear driven via a train of gears coming from the rear of the crankshaft");
                sentenceList.add("I was also amazed to find that the items from the diner car were not so bad at all!");
                sentenceList.add("Many of the men crawl towards the rails, ready to jump into the water to swim ashore.");
                sentenceList.add("When he could finally cofus through the black of the night, he plodded forward, feet wallowing in the soft sand like a boat in a heavy sea.");
                sentenceList.add("Flying into Kona, the plane passes low over turquoise water so clear I can see white sand and Black lava rocks through its shimmering depths");
                sentenceList.add("It is a big aircraft, which acts more like a hot air balloon than airplane, because of its slow speed and reactions");
                sentenceList.add("Joe Creek, which spills into the pacific here, is a wonderful spot to canoe or kayak.");
                sentenceList.add("We run across the rooftop hand in hand and then soar into the air");
                sentenceList.add("Sally was so late for school that she had to run for the bus");
                break;
            case "Beach":
                sentenceList.add("Some station themselves on this side of the pond, some on that, for the poor bird cannot be omnipresent; if he dive here he must come up there");
                sentenceList.add("There were pigs and goats on the island, and plenty of fish could be caught from ashore");
                sentenceList.add("When you left, the demons were pouring in, the lake of Souls was bubbling and everything else was falling apart");
                sentenceList.add("He clared at the ocean and strode up the beach littered with wood, boats, and cars, to the highway.");
                sentenceList.add("Before he set sail for Egypt, the French had taken possession of Rome");
                sentenceList.add("This is not me fighting against the tide of history but being swept along with it");
                sentenceList.add("If you're lucky you might even spy salmon swimming at the fish ladder, which has access to an underwater viewing area");
                sentenceList.add("The coal was conveyed to the works and for shipment to a wharf on the east bank, on the backs of mules and somewhat later by means of private canal.");
                sentenceList.add("She had not had a vacation for twelve years, think of it, and all that time she has been the sunshine of my life.");
                sentenceList.add("To the south of esplanade is a pier of stone on wooden piles, and the Alexandra and other public gardens are attractive.");
                break;
            case "Circus":
                sentenceList.add("Some clown who wanted to give my first novel a one-star review on Amazon, anonymously gets to crap upon it, and have that become part of the history of that book until the end of time");
                sentenceList.add("And Natasha felt that this costume, the very one she had regarded with surprise and amusement at Otradnoe, was just the right thing and not at all worse than a swallow-tail or frock coat");
                sentenceList.add("A great religious festival is held here every twelfth year");
                sentenceList.add("We traipsed up the stairs to the living room, a parade of zombie, each as stunned and speechless as the others ");
                sentenceList.add("She hadn't been able to get through to anyone else and wondered if there was some magic trick he'd done to reach her.");
                sentenceList.add("The hosts this time around are Damian Fahey, host of MTV's Total Request Live, and Myleene Klass, a British entertainer who was a contestant on the U.K. version of I'm a Celebrity Get Me Out of Here.");
                sentenceList.add("Dusty resisted the urge to draw his hand cannon from the small of his back.");
                sentenceList.add("A 10% surtax on the fees of admission to places of public amusement.");
                sentenceList.add("Hannah sat and began talking about Paris again to an audience eager to hear her.");
                sentenceList.add("The boy no doubt inherits a capacity for riding a bicycle, otherwise he could never do so.");
                break;
            case "Jobs":
                sentenceList.add("There was no pain, as promised, but the canine was vcmthe size of butcher knife.");
                sentenceList.add("A teacher doesn't have to be smart, just brighter than the student.");
                sentenceList.add("As a lawyer he was engaged during his later years in most of the especially important cases in the Supreme Court of the United States and in the courts of Maryland.");
                sentenceList.add("The mad scientist is right, we can't let anything living out of this town.");
                sentenceList.add("Martha is a trauma nurse in a large Boston hospital.");
                sentenceList.add("As a scientific engineer and practical architect Wren was perhaps more remarkable than as an artistic designer.");
                sentenceList.add("Schlick goes on to say the organ is to be suited to the choir and properly tuned for singing, that the singer may not be forced to sing too high or too low and the organist have to play chromatics, which is not handy for every one.");
                sentenceList.add("Miss Keller has a braille writer on which she keeps notes and writes letters to her blind friends.");
                sentenceList.add("The kitchen is overseen by a professional chef, but the food is prepared and served by students.");
                sentenceList.add("He is now remembered and honored as the inventor of the steamboat.");
                break;
            case "Weather":
                sentenceList.add("It was a sunny morning and by eight o'clock it was already hot.");
                sentenceList.add("It was frosty and the air was sharp, but toward evening the sky became overcast and it began to thaw.");
                sentenceList.add("The day dawned cloudy and cold with a light rain that chilled her after ten minutes.");
                sentenceList.add("Those of the plains find the temperature chilly, and are stricken down with influenza and pains in the limbs.");
                sentenceList.add("Governments respond to that inflation by freezing prices.");
                sentenceList.add("Additionally, if you happen to get caught in a downpour, or a thunderstorm, a cardigan can help remove some of the chill that comes with a temperature drop.");
                sentenceList.add("New Zealand was poorly stocked with a weak flora; the more robust and aggressive one of the north temperate region was ready at any moment to invade it, but was held back by physical barriers which human aid has alone enabled it to surpass.");
                sentenceList.add("Most people who live in or near a large city are familiar with the look of smog that appears as a thick, gray blanket of air pollution that covers the city.");
                sentenceList.add("She stepped out into the frigid morning, her boots sinking into the snow with a squeaking sound.");
                sentenceList.add("The day dawned cloudy and cold with a light rain that chilled her after ten minutes.");
                break;
            case "Nature":
                sentenceList.add("The metal panels on top of the generator opened like a flower, automatically adjusting themselves to catch the most sun.");
                sentenceList.add("It's private land, not national forest or park lands, and even though you or the Dawkinses own all this, it's not posted, except for the mine tunnel.");
                sentenceList.add("People who buy organic food, for instance, are not doing it simply because they have more money.");
                sentenceList.add("His father died when he was three, his mother when he was only seven, and he grew up in a brutal and degrading environment where he learnt to hold human life and human dignity in contempt.");
                sentenceList.add("This investigator held that the decomposition of the sugar molecules takes place outside the cell wall.");
                sentenceList.add("The organism is made up of molecules which are analogous to them.");
                sentenceList.add("The tropical belt of high pressure persists all the year tion of ture.");
                sentenceList.add("Those who argue they should not say there is no way for poor countries to compete with mechanized Western farming and the extremely high yields it produces.");
                sentenceList.add("In our climate, in the summer, it was formerly almost solely a covering at night.");
                sentenceList.add("He braked carefully as the last of a series of curves came up before the level of a long valley was spread out before him.");
                break;
            case "Music":
                sentenceList.add("He coaxed one melody after another from the old piano, and then finally he folded the lid down.");
                sentenceList.add("He played, but when he tried to sing to the accompaniment of the instrument, his feelings overcame him.");
                sentenceList.add("The Franciscan church, with an elegant tower built in 1866, is an interesting example of the transition style of the 13th century, with later baroque additions.");
                sentenceList.add("Every musician's dream is to one day have a crowd cheer for an encore.");
                sentenceList.add("Her western ensemble was complete with boots and a black Stetson hat.");
                sentenceList.add("Out of all the positions on the squad, Mascots must be able to improvise and really be able to bring out their Joe or Josie character.");
                sentenceList.add("Each composition seems to have been a genuine medley or lanx satura: any topic might be introduced which struck the author's fancy at the moment.");
                sentenceList.add("The Supreme Court so held; its opinion, written by Chief Justice Marshall, being little else than a recital of Webster's argument.");
                sentenceList.add("The song has a slow tempo.");
                sentenceList.add("The vibration of the washing machine made a terrible noise, making it difficult to concentrate.");
                break;
            case "Exercise":
                sentenceList.add("His health, which had hitherto been but indifferent, strengthened with the demands made upon it; his talents, his power of endurance, and his ambition all expanded together.");
                sentenceList.add("Madame Roland took an active part in the political discussions in these reunions.");
                sentenceList.add("He already felt the loss of her calm energy, but he had no idea what to do about it.");
                sentenceList.add("Her years of training led her to a conclusion she couldn't yet embrace: that the only way to hide the large-scaled planning would require someone on the inside of the government.");
                sentenceList.add("They applauded all his tricks and at the end of the performance begged him earnestly not to go away again and leave them.");
                sentenceList.add("Great prostration, vomiting and cold, clammy perspiration follow within one to three hours.");
                sentenceList.add("Aloin is preferable to aloes for therapeutic purposes, as it causes less, if any, pain.");
                sentenceList.add("It was a relief to stretch out in a restaurant booth.");
                sentenceList.add("She started to put the pillow down and caught the movement in the mirror from the corner of her eye.");
                sentenceList.add("From that time he was practically minister of foreign affairs, for Prince Gorchakov was no longer capable of continued intellectual exertion, and lived mostly abroad.");
                break;
            case "Politics":
                sentenceList.add("The president of the Republic has a military household, and the minister a cabinet, both of which are occupied chiefly with questions of promotion, patronage and decorations.");
                sentenceList.add("During his campaign and his time in office, the extent of the effect of his polio was kept from the public, but the fact he had the disease was commonly known.");
                sentenceList.add("After a few words of greeting, each candidate was directed to stand on either side of the raised platform.");
                sentenceList.add("It comprises one-third of the representation in the House of Representatives, and perhaps a still larger proportion in the Senate.");
                sentenceList.add("Under a government which allowed to the people an unprecedented liberty of speech.");
                sentenceList.add("He was a prominent member of the Republican party, and in 1861 was a delegate to the Peace Conference in Washington.");
                sentenceList.add("There he received news of his election as mayor of Bordeaux with a peremptory royal endorsement enjoining residence, and after some time journeyed homewards.");
                sentenceList.add("A constitutional amendment of 1900 dispensed with the session of the legislature at Newport.");
                sentenceList.add("After purchasing a conservative blouse and jeans of quality, she moved on to a less crowded grocery store than the budget one she usually used.");
                sentenceList.add("The allegation about his mother was false: the Pharisee who retailed it was guilty of no small offence.");
                break;
            case "Astronomy":
                sentenceList.add("We will now investigate the total illumination distributed over the area of the circle of radius r.");
                sentenceList.add("They included a perpetual calendar with phases and age of the moon, indication of sunrise and sunset, and a celestial chart depicting the constellations of stars in the sky over Packard's home in Ohio.");
                sentenceList.add("The Greeks created the sciences of geometry and of number as applied to the measurement of continuous quantities.");
                sentenceList.add("The series premiere debuted not with the destruction of Krypton as detailed in the Superman lexicon, but with the meteor shower that brought Kryptonian orphan Kal-El to Earth.");
                sentenceList.add("Tina's youngest son tried his hardest to fly like a superhero, but gravity would not permit it.");
                sentenceList.add("They both protested against the political and ecclesiastical inertia of their native state, and adopted the doctrines of freedom and reason.");
                sentenceList.add("The question is whether, when the adjustment of focus is correct for the central rays of the spectrum, the error of phase for the most extreme rays.");
                sentenceList.add("They are essentially spherical, pear-shaped or oval sacs opening on to the exterior but closed at the coelomic end.");
                sentenceList.add("And its consequent accessibility, there arose a galaxy of scholars under whose influence the archaic style and the ancient Japanese traditions entered a period of renaissance.");
                sentenceList.add("If the magnitude and increasing complexity of these creations fails to impress you, the sheer quantity should suffice.");
                break;
        }
        return sentenceList;
    }
}
