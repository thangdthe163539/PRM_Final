package com.example.prm_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
    ImageView bestFilmImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getContext().deleteDatabase("film_category.db");
        DatabaseFilm dbf = new DatabaseFilm(getContext(), null, null, 1);

        dbf.insertData(4, " Bocchi the Rock! ", " Comedy", "Yearning to make friends and perform live with a band, lonely and socially anxious Hitori Bocchi Gotou devotes her time to playing the guitar. On a fateful day, Bocchi meets the outgoing drummer Nijika Ijichi, who invites her to join Kessoku Band when their guitarist, Ikuyo Kita, flees before their first show. Soon after, Bocchi meets her final bandmate—the cool bassist Ryou Yamada. Although their first performance together is subpar, the girls feel empowered by their shared love for music, and they are soon rejoined by Kita. Finding happiness in performing, Bocchi and her bandmates put their hearts into improving as musicians while making the most of their fleeting high school days.",
        "10", " Oct 9, 2022", 999999, Integer.toString(R.drawable.abocchitherock), Integer.toString(R.raw.abocchitherock));
        dbf.insertData(4, " Tensei Oujo to Tensai Reijou no Mahou Kakumei", " Fantasy", "Princess Anisphia Anis Wynn Palletia has always dreamed of flying through the sky, even though the people of her kingdom consider it a silly ambition. Also at odds with her goal is the fact that Anis is incapable of using magic despite her noble status. Refusing to give up so easily, she renounces her right to the throne, and focuses on developing magicology by combining various resources with knowledge from her previous life on Earth. Due to Anis' nature, responsibility for the kingdom's future is passed onto her younger brother, Algard. Pushed into a political marriage with Euphyllia Magenta, a girl he hardly knows, Algard rebels by spending more time with a commoner girl than his fiancée. After Euphyllia tries to intervene, Algard publicly calls off their marriage and denounces his ex-fiancée as a bully. After crashing into the banquet where Algard makes the announcement, Anis rescues Euphyllia on the flying broom she is testing. She decides to make Euphyllia her assistant, which the other girl reluctantly agrees to. Although their partnership appears random at first, Anis has an ulterior motive for wanting Euphyllia's company.",
                "10", " Jan 4, 2023", 999999, Integer.toString(R.drawable.atenseioujo), Integer.toString(R.raw.atenseioujo));
        dbf.insertData(4, " Mob Psycho 100 III ", "Action", "After foiling a world-threatening plot, Shigeo Mob Kageyama returns to tackle the more exhausting aspects of his mundane life—starting with filling out his school's nerve-racking career form. Meanwhile, he continues to assist his mentor Arataka Reigen and the office's new recruit, Katsuya Serizawa, in solving paranormal cases of their clients. While continuing his duties, Mob also works on gaining more independence in his esper and human lives, as well as trying to integrate better with the people around him. However, new supernatural and ordinary challenges test Mob’s emotional stability and force him to confront the realities around him. As he strives to continue forward on the path to maturity, Mob must resolve his emotional crises and reassess the naivety he has held on for so long.",
                "10", " Oct 6, 2022 ", 999999, Integer.toString(R.drawable.amobpsycho100iii), Integer.toString(R.raw.amobpsycho100iii));
        dbf.insertData(4, " Shadows House 2nd Season ", " Fantasy ", "After the resolution of the debut, Kate and her Doll Emilico have officially become residents of the Shadows House. However, they are under constant vigilance by the Star Bearers—an elite group in charge of the children's wing. In order to escape from their surveillance and the morbid methods they use to keep everyone's loyalty in check, Kate and Emilico must be wary of who to trust and aim to become Star Bearers themselves. Meanwhile, the Star Bearers have encountered their own problems. A mysterious robed figure dubbed Master Robe has trespassed and roams around the children's wing. At first, Master Robe is deemed harmless, but more incidents start occurring that endanger the Dolls' lives—all of them pointing to the suspicious individual. To improve her reputation, Kate decides to solve the mystery herself. Yet, with so few clues and so many suspects, searching for Master Robe and their motive for attacking the mansion proves more challenging than she imagined.",
                "10", " Jul 9, 2022 ", 999999, Integer.toString(R.drawable.ashadowshouse2nd), Integer.toString(R.raw.ashadowshouse2nd));
        dbf.insertData(4, " Machikado Mazoku: 2-choume", " Comedy ", "Second season of Machikado Mazoku.                ",
                "10", " Apr 8, 2022 ", 999999, Integer.toString(R.drawable.amachikadomazoku2), Integer.toString(R.raw.amachikadomazoku2));
        dbf.insertData(4, " Akebi-chan no Sailor-fuku ", " Slice of Life ", "Ever since she was young, Komichi Akebi has always adored sailor uniforms, even going so far as to ask her mother to sew one if she succeeds in getting into her mother's alma mater, Roubai Academy. And thus, when she gets accepted into the prestigious school, Komichi is ecstatic. However, much to her surprise, the middle school no longer uses sailor uniforms as its dress code—making Komichi stand out from her schoolmates. Despite this, Komichi is granted permission to continue wearing the traditional attire. With renewed confidence, Komichi meets fascinating classmates as they experience school life together. Under the colorful shower of blossoming prospects, an exciting tomorrow awaits them!",
                "10", " Jan 9, 2022 ", 999999, Integer.toString(R.drawable.aakebichannosailorfuku), Integer.toString(R.raw.aakebichannosailorfuku));
        dbf.insertData(4, " 5-toubun no Hanayome ∬ ", "Romance", "Through their tutor Fuutarou Uesugi's diligent guidance, the Nakano quintuplets' academic performance shows signs of improvement, even if their path to graduation is still rocky. However, as they continue to cause various situations that delay any actual tutoring, Fuutarou becomes increasingly involved with their personal lives, further complicating their relationship with each other. On another note, Fuutarou slowly begins to realize the existence of a possible connection between him and the past he believes to have shared with one of the five girls. With everyone's feelings beginning to develop and overlap, will they be able to keep their bond strictly to that of a teacher and his students—or will it mature into something else entirely?",
                "10", " Jan 8, 2021 ", 999999, Integer.toString(R.drawable.a5toubunnohanayome), Integer.toString(R.raw.a5toubunnohanayome));
        dbf.insertData(4, " Shoujo Shuumatsu Ryokou", "Science", "Amid the desolate remains of a once-thriving city, only the rumbling of a motorbike breaks the cold winter silence. Its riders, Chito and Yuuri, are the last survivors in the war-torn city. Scavenging old military sites for food and parts, the two girls explore the wastelands and speculate about the old world to pass the time. Chito and Yuuri each occasionally struggle with the looming solitude, but when they have each other, sharing the weight of being two of the last humans becomes a bit more bearable. Between Yuuri's clumsy excitement and Chito's calm composure, their dark days get a little brighter with shooting practice, new books, and snowball fights on the frozen battlefield. Among a scenery of barren landscapes and deserted buildings, Shoujo Shuumatsu Ryokou tells the uplifting tale of two girls and their quest to find hope in a bleak and dying world.",
                "10", " Oct 6, 2017 ", 999999, Integer.toString(R.drawable.ashoujoshuumatsuryokou), Integer.toString(R.raw.ashoujoshuumatsuryokou));
        dbf.insertData(4, " Saiki Kusuo no Ψ-nan 2 ", " Comedy", "The disastrous life of the gifted psychic Kusuo Saiki continues, despite his utmost effort to live an ordinary life. Although he has certainly grown accustomed to dealing with his troublesome friends—who are his biggest hurdle to achieving a peaceful life—he still has a long way to go. Also joining the usual oddballs are a few new faces whose shenanigans add to Saiki's misery, making his dreams of a hassle-free life a distant fantasy.",
                "10", " Jan 17, 2018", 999999, Integer.toString(R.drawable.asaikikusuonosainan2), Integer.toString(R.raw.asaikikusuonosainan2));
        dbf.insertData(4, " Yagate Kimi ni Naru ", "Romance", "Yuu Koito has always been entranced with romantic shoujo manga and the lyrics of love songs. She patiently waits for the wings of love to sprout and send her heart aflutter on the day that she finally receives a confession. Yet, when her classmate from junior high declares his love for her during their graduation, she feels unexpectedly hollow. The realization hits her: she understands romance as a concept, but she is incapable of experiencing the feeling first-hand. Now, having enrolled in high school, Yuu, disconcerted and dispirited, is still ruminating over how to respond to her suitor. There, she happens upon the seemingly flawless student council president, Touko Nanami, maturely rejecting a confession of her own. Stirred by Touko's elegant manner, Yuu approaches her for advice, only to be bewildered when the president confesses to her! Yuu quickly finds herself in the palm of Touko's hand, and unknowingly sets herself on a path to find the emotion which has long eluded her.",
                "10", " Oct 5, 2018 ", 999999, Integer.toString(R.drawable.ayagatekimininaru), Integer.toString(R.raw.ayagatekimininaru));
        dbf.insertData(4, " Yakusoku no Neverland ", " Science ", "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their Mama, Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage—a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family, never to be heard from again. However, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.",
                "10", " Jan 10, 2019 ", 999999, Integer.toString(R.drawable.ayakusokunoneverland), Integer.toString(R.raw.ayakusokunoneverland));
        dbf.insertData(4, " Hitoribocchi no Marumaru Seikatsu ", " Comedy ", "Many of us know what it is like to transition to a new school with few to no friends in a new environment, going through the arduous process of getting to know people again. Bocchi Hitori knows this struggle all too well, having just graduated from elementary school and thrown into middle school. Unfortunately, she suffers from extreme social anxiety: she faints when overwhelmed, vomits when nervous, and draws up ridiculously convoluted plans to avoid social contact. It does not help that her only friend from elementary school, Kai Yawara, will not be attending the same middle school as Bocchi. However, wanting to help her, Kai severs ties with Bocchi and promises to reconcile with her when she befriends all of her classmates in her new middle school class. Even though Bocchi has no faith in herself, she is determined to be friends with Kai again. Summoning all of her courage, Bocchi takes on the daunting challenge of making friends with her entire class, starting with the delinquent-looking girl sitting in front of her...",
                "10", " Apr 6, 2019 ", 999999, Integer.toString(R.drawable.ahitoribocchinomarumaruseikatsu), Integer.toString(R.raw.ahitoribocchinomarumaruseikatsu));
        dbf.insertData(4, " Rewrite 2nd Season ", " Action "," The second season of the Rewrite series which adapts Moon and Terra routes.",
                "10", " Jan 14, 2017 ", 999999, Integer.toString(R.drawable.arewrite2ndseason), Integer.toString(R.raw.arewrite2ndseason));
        dbf.insertData(4, " Karakai Jouzu no Takagi-san 2 ", " Comedy ", "Even after spending a considerable amount of time with Takagi, Nishikata is still struggling to find a perfect plan to defeat the expert teaser. A battle of wits, a contest of physical prowess, a test of courage—any strategy he employs to expose her weaknesses is to no avail. On the contrary, Nishikata's pitiful attempts only reveal more of his own flaws, which Takagi takes advantage of to become increasingly daring in her teasing attempts. To make things worse for Nishikata, rumors about him and Takagi may have spread in class due to the frequent interactions between them. However, the optimistic Nishikata believes that wisdom comes with age and that as the days go by, his experience with her constant teasing will eventually bear fruit, leading him to the awaited moment of victory. Thus, Nishikata continues to strive for the seemingly impossible—to outsmart Takagi and make her blush with embarrassment.",
        "10", " Jul 7, 2019 ", 999999, Integer.toString(R.drawable.akarakaijouzunotakagisan2), Integer.toString(R.raw.akarakaijouzunotakagisan2));
        dbf.insertData(4, " Bokutachi wa Benkyou ga Dekinai! ", "Romance", "Under Nariyuki Yuiga's devoted tutelage, his classmates Rizu Ogata, Fumino Furuhashi, and Uruka Takemoto are finally pulling average test scores on their worst subjects. But time is ticking, and there is still a long way to go before the three geniuses of Ichinose Academy are ready for their upcoming university exams. Meanwhile, the girls still struggle to balance the pursuit of their dreams with their growing affections for their unsuspecting tutor. Joining them are Mafuyu Kirisu, a teacher with strong views about education and talent because of her past as a rising figure skater, and Asumi Kominami, a graduate from their school aiming to attend a national medical university. With these two additions, the group of six is livelier than ever before. Completely caught up in hilarious antics with his new friends, Yuiga finds that his last year of high school now includes a lot more than just going to class and studying.",
              "10", " Oct 6, 2019 ", 999999, Integer.toString(R.drawable.abokutachiwabenkyougadekinai), Integer.toString(R.raw.abokutachiwabenkyougadekinai));
        dbf.insertData(4, " Tsugu Tsugumomo ", " Action ", "When ordinary boy Kagami Kazuya meets the beautiful tsukumogami Kiriha, his life gets turned upside-down. As a Taboo Child who draws the supernatural towards him, he receives orders from the God of the Land, Kukuri, to become an exorcist and defeat these evil forces. And so, he and Kiriha do battle. To find out information on these supernatural beings, Kazuya and his friends set up a counselor's club at school. But behind the typical-seeming troubles he hears about, he uncovers a major plot to target Kukuri... In addition to the sadistic-yet-beautiful tsukumogami Kiriha, the situation draws other girls to Kazuya to join the fray!",
              "10", " Apr 5, 2020 ", 999999, Integer.toString(R.drawable.atsugutsugumomo), Integer.toString(R.raw.atsugutsugumomo));
        dbf.insertData(4, " Majo no Tabitabi ", " Fantasy ", "Since childhood, Elaina has always been fascinated by the stories written within her favorite book, especially those about Nike, a renowned witch who had numerous great travels across the world. Wanting to experience the awe of adventure herself, Elaina strives to become a witch, and despite the numerous trials that come her way, she eventually succeeds. Now a full-fledged witch, Elaina finally embarks on her long-awaited journey, in which she meets many people along the way, learning their various stories. Through all of this, she explores the world at its fullest—experiencing both its bright and dark sides—starting her legendary tale.",
              "10", " Oct 2, 2020 ", 999999, Integer.toString(R.drawable.amajonotabitabi), Integer.toString(R.raw.amajonotabitabi));



        dbf.insertData(4, "Kimi No Na Wa", "Romance", "In 2013, Mitsuha Miyamizu is a high school girl living in the rural town of Itomori, Japan. Bored of the town, she wishes to be a Tokyo boy in her next life. One day, she inexplicably begins to switch bodies intermittently with Taki Tachibana, a high school boy in Tokyo. Thus, when they wake up as each other on some mornings, they must live through the other's respective activities and social interactions for the day. They learn they can communicate with each other by leaving messages on paper, phones, and sometimes on each other's skin. Mitsuha (in Taki's body) sets Taki up on a date with coworker Miki Okudera, while Taki (in Mitsuha's body) causes Mitsuha to become popular at school. One day, Taki (in Mitsuha's body) accompanies Mitsuha's grandmother Hitoha and younger sister Yotsuha to leave the ritual alcohol kuchikamizake, made by the sisters, as an offering at the Shinto shrine located on a mountaintop outside the town. It is believed to represent the body of the village guardian god ruling over human connections and time. Taki reads a note from Mitsuha about the comet Tiamat, expected to pass nearest to Earth on the day of the autumn festival. The next day, Taki wakes up in his body and goes on a date with Miki, who tells him she enjoyed the date but also that she can tell he is preoccupied with thoughts of someone else. Taki attempts to call Mitsuha on the phone but cannot reach her as the body-switching ends.",
                "9.7", "August 26, 2016", 93324, Integer.toString(R.drawable.yourname), Integer.toString(R.raw.yourname));
        dbf.insertData(5, "Stein Gate:0", "Fantasy", "The series is a final iteration of Rintaro Okabe's experiences in the beta world line after the iterations depicted in the Steins;Gate 0 visual novel. It takes place in an alternative world line from the original Steins;Gate where Rintaro Okabe fails to save Kurisu Makise and doesn't receive any guidance from his future self nor encouragement from Mayuri Shiina. After failing to save Kurisu Makise in order to prevent a future war over time machines, Rintaro Okabe, traumatized over his experiences of meddling with the past with his Reading Steiner ability, accepts his life in the beta world line where Kurisu stays dead. After several months have passed, Rintaro meets Maho Hiyajo and Alexis Leskinen, two of Kurisu's former colleagues who have been working on Amadeus, an artificial intelligence system using Kurisu's memories from before her death. Rintaro accepts a request to help out with Amadeus' development by becoming a tester, conversing with the Amadeus Kurisu through his phone",
                "9.4", "April 12, 2018", 233423, Integer.toString(R.drawable.steingate0), Integer.toString(R.raw.steingate));
        dbf.insertData(8, "No Game No Life: Zero", "Science", "In the present, Izuna and Tet play a game of chess on which they have wagered food. As they begin their next game, Tet decides to tell her the story of how the world came to be in its current state.\n" +
                        "\n" +
                        "The tale begins 6000 years in the past, during the Great War, a worldwide conflict that pitted the sentient races of the world against each other as the Old Deus fought for control of the Suniaster, a conceptual device which would only reveal itself to the strongest being on the planet and make the holder the One True God. To that end, the Old Deus and the other races have effectively destroyed the world, and have driven humankind, the only race unable to use the world's magic, to the brink of extinction. Riku, the leader of humanity's last colony along with his sister Corounne, allows another one of his companions die in a Demonia attack while sourcing for information; the death, along with all the others that have come before, plague him with nightmares and guilt. The colony he leads finds itself on the verge of annihilation, with their leader unstable, their numbers dropping, and the battles that endanger their lives around the area becoming more frequent and drawing ever closer.\n" +
                        "\n" +
                        "Delving into a ruined Elvish hideout the next day to investigate, Riku stumbles upon an Ex-Machina that has been severed from its race's hive-mind for its efforts to understand the human heart, a concept that defies its machine logic and thus could not be calculated by the hive-mind. Initially hesitant to help her, Riku eagerly accepts when she challenges him to a game of chess, despite knowing the frightening processing power the Ex-Machina possess; predictably, he loses, and they agree to cooperate with each other, allowing him to take advantage of her logical prowess and letting her stay with him to learn more about the human heart. Since she has only a long, droning designation code assigned to her, Riku, hearing the word \"Schwarzer\" (German for \"black\") in her title, names her Schwi after the color of her hair.",
                "9.9", "July 15, 2017", 12345312, Integer.toString(R.drawable.nogamenolife), Integer.toString(R.raw.nogamenolife));

        dbf.signUp("phong", "a@a.com", "123321");
        dbf.signIn("a@a.com","123321");
        Film filmBest = dbf.filmBest();
        bestFilmImage = view.findViewById(R.id.imagebestFilm);
        films = dbf.getListTreding();
        btn = view.findViewById(R.id.filmNameBest);
        btn.setText(filmBest.getName());
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), watchFilmFullScreen.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("filmbest", filmBest);
            intent.putExtras(bundle);
            startActivity(intent);
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