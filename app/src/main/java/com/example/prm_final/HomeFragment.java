package com.example.prm_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    RecyclerView recyclerView;
    ArrayList<Film> films;
    Button btn;
    TextView bestFilm;
    ImageView bestFilmImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatabaseFilm dbf = new DatabaseFilm(getContext(), null , null , 1);

//        dbf.insertData(1, "Dr.Strange 2", "Action",  "America Chavez and a version of Stephen Strange are chased by a demon in the space between universes while searching for the Book of Vishanti. That Strange is killed and Chavez accidentally creates a portal that transports herself and Strange's corpse to Earth-616, where that universe's version of Strange rescues Chavez from another demon with help from the Sorcerer Supreme, Wong. Chavez explains that the beings are hunting her because she has the power to travel through the multiverse.",
//                "9.6", "April 5, 2022", 100213, Integer.toString(R.drawable.drstrange2), Integer.toString(R.raw.dr_strange2));
//        dbf.insertData(2, "Black Panther", "Action", "Black Panther is a 2018 American superhero film based on the Marvel Comics character of the same name. Produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures, it is the 18th film in the Marvel Cinematic Universe (MCU). The film was directed by Ryan Coogler, who co-wrote the screenplay with Joe Robert Cole, and it stars Chadwick Boseman as T'Challa / Black Panther alongside Michael B. Jordan, Lupita Nyong'o, Danai Gurira, Martin Freeman, Daniel Kaluuya, Letitia Wright, Winston Duke, Angela Bassett, Forest Whitaker, and Andy Serkis. In Black Panther, T'Challa is crowned king of Wakanda following his father's death, but he is challenged by Killmonger (Jordan), who plans to abandon the country's isolationist policies and begin a global revolution.\n" +
//                "Wesley Snipes planned to make a Black Panther film in 1992, but the project did not come to fruition. In September 2005, Marvel Studios listed a Black Panther film as one of ten films based on Marvel characters intended to be distributed by Paramount Pictures. Mark Bailey was hired to write a script in January 2011. Black Panther was officially announced in October 2014, and Boseman made his first appearance as the character in Captain America: Civil War (2016). Cole and Coogler had joined by then, with additional casting in May. Black Panther is the first Marvel Studios film with a Black director and a predominantly Black cast. Principal photography took place from January to April 2017 at EUE/Screen Gems Studios in the Atlanta metropolitan area, and in Busan, South Korea",
//                "9.8", "January 29, 2018",98334, Integer.toString(R.drawable.blackpanther), Integer.toString(R.raw.black_panther));
//        dbf.insertData(3, "Venom 2", "Horror", "In 1996, a young Cletus Kasady watches helplessly as his lover, Frances Barrison, is taken away from St. Estes Home for Unwanted Children to the Ravencroft Institute. On the way, Barrison uses her sonic scream powers to attack young police officer Patrick Mulligan. Mulligan shoots Barrison in the eye and suffers an injury to his ear due to her scream. Unbeknownst to Mulligan, who believes he killed her, Barrison is still taken to Ravencroft where her abilities are restricted.\n" +
//                "\n" +
//                "In the present day, Mulligan is now a detective and asks journalist Eddie Brock to speak to serial killer Kasady in San Quentin State Prison, as Kasady refuses to talk to anyone other than Brock. After the visit, Brock's alien symbiote Venom deduces where Kasady has hidden the bodies of his victims, which gives Brock a huge career boost. Brock is then contacted by his ex-fiancée Anne Weying, who tells him that she is now engaged to Dr. Dan Lewis, to Venom's displeasure. Kasady, who has been found guilty of his crimes and sentenced to death by lethal injection, invites Brock to attend his execution. Brock speaks with Kasady, who insults Brock, provoking Venom to attack Kasady. Kasady bites Brock's hand and ingests a small part of the symbiote. Back at their home, Venom has an argument with Brock about wanting to have more freedom to eat criminals, and the symbiote decides to leave Brock's body and go off on its own.",
//        "9.2", "October 1, 2021", 9334, Integer.toString(R.drawable.venom2), Integer.toString(R.raw.venom2));
//        dbf.insertData(4, "Kimi No Na Wa", "Romance", "In 2013, Mitsuha Miyamizu is a high school girl living in the rural town of Itomori, Japan. Bored of the town, she wishes to be a Tokyo boy in her next life. One day, she inexplicably begins to switch bodies intermittently with Taki Tachibana, a high school boy in Tokyo. Thus, when they wake up as each other on some mornings, they must live through the other's respective activities and social interactions for the day. They learn they can communicate with each other by leaving messages on paper, phones, and sometimes on each other's skin. Mitsuha (in Taki's body) sets Taki up on a date with coworker Miki Okudera, while Taki (in Mitsuha's body) causes Mitsuha to become popular at school. One day, Taki (in Mitsuha's body) accompanies Mitsuha's grandmother Hitoha and younger sister Yotsuha to leave the ritual alcohol kuchikamizake, made by the sisters, as an offering at the Shinto shrine located on a mountaintop outside the town. It is believed to represent the body of the village guardian god ruling over human connections and time. Taki reads a note from Mitsuha about the comet Tiamat, expected to pass nearest to Earth on the day of the autumn festival. The next day, Taki wakes up in his body and goes on a date with Miki, who tells him she enjoyed the date but also that she can tell he is preoccupied with thoughts of someone else. Taki attempts to call Mitsuha on the phone but cannot reach her as the body-switching ends.",
//                "9.7", "August 26, 2016", 93324, Integer.toString(R.drawable.yourname), Integer.toString(R.raw.yourname));
//        dbf.insertData(5,  "Stein Gate:0", "Fantasy", "The series is a final iteration of Rintaro Okabe's experiences in the beta world line after the iterations depicted in the Steins;Gate 0 visual novel. It takes place in an alternative world line from the original Steins;Gate where Rintaro Okabe fails to save Kurisu Makise and doesn't receive any guidance from his future self nor encouragement from Mayuri Shiina. After failing to save Kurisu Makise in order to prevent a future war over time machines, Rintaro Okabe, traumatized over his experiences of meddling with the past with his Reading Steiner ability, accepts his life in the beta world line where Kurisu stays dead. After several months have passed, Rintaro meets Maho Hiyajo and Alexis Leskinen, two of Kurisu's former colleagues who have been working on Amadeus, an artificial intelligence system using Kurisu's memories from before her death. Rintaro accepts a request to help out with Amadeus' development by becoming a tester, conversing with the Amadeus Kurisu through his phone",
//                "9.4", "April 12, 2018", 233423, Integer.toString(R.drawable.steingate0), Integer.toString(R.raw.steingate));
//        dbf.insertData(6, "Sex Education 3", "Romance",  "Sex Education primarily follows Otis Milburn, a student at Moordale Secondary School. Otis begins the series ambivalent about sex, in part because his single mother Jean is a sex therapist who frequently has affairs with male suitors but is unable to maintain romantic relationships.\n" +
//                "\n" +
//                "Other students at Moordale include Eric Effiong, Otis' best friend and the gay son of Ghanaian-Nigerian immigrants; Maeve Wiley, a rebellious teen whose independence is overshadowed by her troubled past; Adam Groff, the headmaster's son who develops a bullying nature out of his own self-loathing; Jackson Marchetti, the head boy struggling to meet the high expectations set for him; Ruby Matthews, Anwar Bakshi and Olivia Hanan, members of a popular clique known as \"The Untouchables\"; Aimee Gibbs, an Untouchable who secretly befriends Maeve; and Lily Iglehart, an eccentric girl determined to lose her virginity. The school is later joined by Ola Nyman, whose widowed father Jakob begins a relationship with Jean.\n" +
//                "\n" +
//                "In the first series, Otis sets up a sex therapy clinic with Maeve to help the students of Moordale with their sexual problems.[3][4] Their business becomes a success but conflict arises when Otis finds himself becoming attracted to Maeve.",
//        "9.0", "11 January 2019", 2334223, Integer.toString(R.drawable.sexeducation3), Integer.toString(R.raw.sexeducation3));
//        dbf.insertData(7, "Mobius (2022)", "Science", "At a hospital in Greece, 10-year-old Michael Morbius welcomes his surrogate brother Lucien, whom he renames Milo; they bond over their shared blood illness and desire to be \"normal\". Their adoptive father and hospital director Nicholas arranges for Morbius to attend medical school in New York while he focuses on caring for Milo.\n" +
//                "\n" +
//                "25 years later, Morbius publicly declines a Nobel Prize for his work with synthetic blood. His colleague Martine Bancroft discovers he has secretly captured dozens of vampire bats from Costa Rica in the hope of splicing their genes with his own to cure his condition. After informing Milo of his planned illegal experiment, Morbius receives funding from him to outfit a private mercenary vessel in international waters with his equipment. While the cure works, it transforms Morbius into a vampire, who kills and drains the crew of their blood after they attack him out of fear. Once his bloodlust subsides and he regains his senses, a horrified Morbius erases all CCTV footage of his experiment before contacting the authorities and jumping overboard.",
//        "9.5", "March 10, 2022", 123453, Integer.toString(R.drawable.mobius), Integer.toString(R.raw.mobius));
//        dbf.insertData(8, "No Game No Life: Zero", "Science", "In the present, Izuna and Tet play a game of chess on which they have wagered food. As they begin their next game, Tet decides to tell her the story of how the world came to be in its current state.\n" +
//                "\n" +
//                "The tale begins 6000 years in the past, during the Great War, a worldwide conflict that pitted the sentient races of the world against each other as the Old Deus fought for control of the Suniaster, a conceptual device which would only reveal itself to the strongest being on the planet and make the holder the One True God. To that end, the Old Deus and the other races have effectively destroyed the world, and have driven humankind, the only race unable to use the world's magic, to the brink of extinction. Riku, the leader of humanity's last colony along with his sister Corounne, allows another one of his companions die in a Demonia attack while sourcing for information; the death, along with all the others that have come before, plague him with nightmares and guilt. The colony he leads finds itself on the verge of annihilation, with their leader unstable, their numbers dropping, and the battles that endanger their lives around the area becoming more frequent and drawing ever closer.\n" +
//                "\n" +
//                "Delving into a ruined Elvish hideout the next day to investigate, Riku stumbles upon an Ex-Machina that has been severed from its race's hive-mind for its efforts to understand the human heart, a concept that defies its machine logic and thus could not be calculated by the hive-mind. Initially hesitant to help her, Riku eagerly accepts when she challenges him to a game of chess, despite knowing the frightening processing power the Ex-Machina possess; predictably, he loses, and they agree to cooperate with each other, allowing him to take advantage of her logical prowess and letting her stay with him to learn more about the human heart. Since she has only a long, droning designation code assigned to her, Riku, hearing the word \"Schwarzer\" (German for \"black\") in her title, names her Schwi after the color of her hair.",
//                "9.9", "July 15, 2017", 12345312, Integer.toString(R.drawable.nogamenolife), Integer.toString(R.raw.nogamenolife));
//
        Film filmBest = dbf.filmBest();
        bestFilmImage = view.findViewById(R.id.imagebestFilm);
        bestFilmImage.setImageResource(Integer.parseInt(filmBest.getImage()));
        films = dbf.getListTreding();
        btn = view.findViewById(R.id.filmNameBest);
        btn.setText(filmBest.getName());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), watchFilmFullScreen.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("filmbest", filmBest);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView = view.findViewById(R.id.recyclerTreding);
        recyclerAdapterTrending adapter = new recyclerAdapterTrending(films, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}