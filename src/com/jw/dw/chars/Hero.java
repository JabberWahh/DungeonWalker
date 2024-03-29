package com.jw.dw.chars;

import com.jw.dw.Items.*;
import com.jw.dw.randInt;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Hero implements Characters {
    private Integer hp;
    private Integer dmg;
    //public boolean needRest = false;
    public static final String icon = "☺";
    public Integer posX = 0;
    public Integer posY = 0;
    public boolean mooved;
    private static Hero instance;
    public String heroName;
    public int lvl = 1;
    public int timeToResurect = 100;
    public long xp = 0;
    public int deathCount = 0;
    public int monstersKilled = 0;
    public Enemy strongestEnemy;
    public Weapon weapon;
    public ArrayList<Armour> armour;
    public ArrayList<Flask> flasks;


    private Hero() {
        this.heroName = GetHeroName();
        SetDmg();
        setHP();
        armour = new ArrayList<>();
        flasks = new ArrayList<>();

        //////
        flasks.add(new Flask(FlaskKind.HealTime));
        flasks.add(new Flask(FlaskKind.HealPermanent));
        flasks.add(new Flask(FlaskKind.Armor));
        flasks.add(new Flask(FlaskKind.Damage));
        flasks.add(new Flask(FlaskKind.PlusHP));
    }

    public static Hero getInstance() {
        if (instance == null) {
            instance = new Hero();
        }
        return instance;
    }

    public void setHP(Integer shp) {

        hp = shp;
    }

    public void setHP() {

        hp = (int) (100 * ((lvl * 0.1) + 0.9));
    }

    public void addHP(int pl) {

        hp = Math.min(hp + pl, getMAXHP());

    }

    public int getMAXHP() {

        return (int) (100 * ((lvl * 0.1) + 0.9));
    }

    public int getNextLvlExp(){
        return  (int)(lvl / 1.5 * 100);
    }

    public Integer getDmg() {

        if (weapon != null) {
            return randInt.GetRandInt((int) (weapon.getStansartDmg() * 0.6), (int) (weapon.getStansartDmg() * 1.4));
        }
        return randInt.GetRandInt((int) (dmg * 0.6), (int) (dmg * 1.4));
    }

    public double getStandartDmg() {

        if (weapon != null) {
            return weapon.getStansartDmg();
        }
        return dmg;
    }

    public double getStandartArmour() {

        double armour = 0;
        for (int i = 0; i < Hero.getInstance().armour.size(); i++) {
            armour = armour + Hero.getInstance().armour.get(i).getStansartArmour();
        }
        return armour;
    }


    public void SetDmg() {
        //dmg = (int) (5 * ((lvl * 0.1) + 0.1));
        dmg = 3 + (lvl * 2) - 1;
    }

    public Integer getHP() {

        return hp;
    }

    public void SetPosition(int x, int y) {
        posX = x;
        posY = y;
    }

    public String getArmourByKind(ArmourPart ap) {

        for (Armour anArmour : armour) {
            if (anArmour.armourPart == ap) {
                return anArmour.armourName + " lvl." + anArmour.lvl;
            }
        }
        return "";
    }

    public Color getArmourColorByKind(ArmourPart ap) {

        for (Armour anArmour : armour) {
            if (anArmour.armourPart == ap) {
                return anArmour.color;
            }
        }
        return Color.rgb(0, 0, 0);
    }

    private static String GetHeroName() {

        String allNames = "Abban Adomn Adhamh Adhamhnán Adamnan Eunan Adanodan Ailbhe Ailbe Alby " +
                "Ailgel Ailill Ailín Aininn Ainmire Airechtach Airmedach Alabhaois Alaios " +
                "Alastrann Alchad Alstrom Amalgaid Amergin Anluan Anlon Aodh Aodha Aoidh " +
                "Aedus Aodhaigh Aodhán Aodán Áedán Aiden Aidan Edan Aodhagán Aogán Egan " +
                "Aodhfin Aonghas Engus Aonas Angus Ardar Árdghal Ardal Argal Artegal " +
                "Arthgallo Art Artan bear Artúr Artuir Arthur Baeth Baetan Balor Bairne " +
                "Baithaus Banan Banbhan Baothghalach Barrfind Bairrfhionn Barrin Bairre " +
                "Barre Barra Barry Beacán Beag Beairtle Beanón Bearach Berach Bearchán " +
                "Beartlaí Becan Behellagh Behillagh Benen Beolagh Beothach Bercan Berchan " +
                "Bercnan Bergin Blanaid Boethis Bran Brandubh Brandrub Branduff Breadán " +
                "Bréanainn Breandán Brendan Bresal Bressal Brasil Breas Breasal Brian Brion " +
                "Bricc Britanmael Brón Bruaidheadh Bruatur Bruddai Buadhach Buagh Buaigre " +
                "Cadhla Caeilte Caentigern Kentigern Cainchinne Cainnech Cainneach Canice " +
                "Cairbre Coirbre Cairpre Caircil Cearcill Caireall Cairell Coireall Kerill " +
                "Cairthinn Caiside Calbhach Calvagh Calbach Callough Caoilte Caoimhe " +
                "Caiomhín Caoimhín Caiomheán Caomhgain Cáemgen Coemgen Kevin Kevan Caolán " +
                "Caolainn Kelan Caraid Carantoc Cárthach Cartach Cartagh Carthagh Cascorach " +
                "Cassidan Cathal Cahal Cathald Catheld Cathaldus Kathel Cathan Cahan " +
                "Cathaoir Cahir Cathfer Catharnach Cathrannach Cathasach Cathbadh Cathbad " +
                "Cathbharr Ceallach Ceallagh Ceallachán Ceollach Cellach Kellach Kelly " +
                "Ceall Cearbhall Cerbhall Cerball Cearul Cearull Carroll Cearbhallán Cearbh " +
                "Cearnach Cedach Celsus Celtchair Cenn Cesarn Chattan Chulain Cian Kian " +
                "Kean Keane Cianán Ciabh Ciárán Kieran Ciardan Ciardha Ciardubhán Ciarrai " +
                "Cillian Cillín Cillne Killian Kealan Kilian Killan Cinneíde Cinneídigh " +
                "Cinneíddin Cionadh Cionaodh Kenneth Cnán Coan Cobhran Cognat Colcu " +
                "Comhghall Comhgall Comgal Comgell Congal Cowal Comhghán Comghán Comgán " +
                "Comman Congan Conaing Conall Connell Conan Conant Conchobar Conchobhar " +
                "Conchubhar Conchubor Conchúr Cnochúr Conquhare Conaire Connor Conor " +
                "Congalach Conganchas Conn Connlaeth Connlaoth Connlaodh Connlaogh Connlaoi " +
                "Conleat Conla Conley Coplait Cormac Cormacc Corb Cothric Couleth Cridan " +
                "Crimhthann Crofinn Cromanus Crónán Cronin Crosson Cruamthain Cuán little " +
                "hound Cuileann Cuileán Cuilén Cuilinn Cullen Cuilenn Cuimín Comyn Cuinn " +
                "Cuirithir Cumall Cúmhaighe Cúmhaí Curoi Curran Cuthacar Daig Daigh Davin " +
                "Dálach Daly Daley Damaen Daman Dámnh Dara Darach Daragh Darragh Darrah " +
                "Daire Déaglán Declan Deicola Dela Demna Desle Desmond Desmumnach Deasún " +
                "Dessi Munster Devlin Diarmait Duirmhuid Diamit Diarmaid Diarmuit Dermot " +
                "Kermit Darby Dieul Dimnaus Disisbod Diuma Dimma Doibhilin Doireidh " +
                "Domhnall Domnall Donál Dónall Dónall Donn Don Donnabhán Donnán Donan " +
                "Donnchadh Donnchad Donncha Dunchad Donagh Donogh Donough Dunecan " +
                "Donndubhán Donndubán Donovan Donngal Doran Deoradhán Dorchaidh Dubhaltach " +
                "Dubaltach Dubultach Dualtach Dualta Duald Dubhán Duban Duane Dowan " +
                "Dubhdara Dubhdarach Dubheidir Dubghall Dubgall Dougal Dubhghlas Dubhglas " +
                "Dughlas Douglas Duigenan Dungal Eachann Ea Eachdhonn Éadbhard Éanna Énnae " +
                "Enda Earna Earnán Echen Éibhear Éibhir Heber Éigneach Éigneachán Eimar " +
                "Eiméid Éimhín Éimíne Evin Evan Eirn Ernin Ernan Eithear Elochad Emianus " +
                "Ennae Eochaidh Echaid Eogabail Eóghan Eogan Eolus Erc Ercus Eremon Fachnan " +
                "Fachtna Fiachna Faebhar Fáilbe Fáilbhe Failge Faolán Felan Foelan Fillan " +
                "Farann Faughnan Feagh Fearadhach Fearadagh Farry Feardorcha Ferdorcha " +
                "Fardoragh Fearghal Fearghall Fergal Ferghil Ferol Fearghas Fearghus Fergus " +
                "Feichín Fehin Fechin Fiach Fiachne Feidhlim Feidhlimidh Fedelmid Feidlim " +
                "Féilim Felim Felimy Feuillan Fillan Fiachra Ficare Fiach Findan Finegas " +
                "Fingar Fínghín Finghin Fingin Fínín Fineen Finn Fionn Fionnán Finnian " +
                "Finian Finnén Fionán Fianan Finnachta Finnchad Finntan Fionntán Fintan " +
                "Fiontan Fiontán Fionnbhárr Fionnbhar Findbarr Finbar Bairre Barra Fios " +
                "Flaithbertach Flaithrí Flurry Florry Flann Flainn Flannán Flannacán " +
                "Fochmare Fogartach Foillas Forgael Forgall Fortchern Frainc Froichan " +
                "Fuatach Fulan Firlan Fursa Fursey Gaithan Gall Goll Gallech Garbhán Garvan " +
                "Garfhidh Garnard Garnat Gilian Glaisne Glassan Gnathach Gob Gobann Goban " +
                "Gordan Gosan Gusan Gráda Guaire Herygh Hewney Huydhran Iarbonel Iarlaith " +
                "Iarlaithe Iarfhlaith Jarlath Ior Iobhar Ibhor Ibor Abaris Ighneachán " +
                "Imchath Incha Indract Ingnathach Ióéil Íosóg Irial Irimia Iucharba Iúd " +
                "Iúil Joavan Kenncoh Kescog Labhcás Labraid Labhraidh Lowry Labhre Lachtna " +
                "Laegh Leagh Laoghaire Legaire Laeghaire Leary Laistranus Laoighseach " +
                "Laoiseach Laois Leinster Molaisse Molaise Lavren Leann Lenn Lithgean " +
                "Lochlainn Lochlann Leachlainn Laughlin Loghlin Lodan Lomán Lonán Lorcán " +
                "Lorcann Lorccán Lua Luchta Lugh Lughaidh Lugaidh Lughaid Lugaid Luger Lewy " +
                "Lysagh Machar Maduta Maedoc Maidhc Maodhg Mairid Manchan Manus " +
                "Mathghamhain Mathúin Mathghamhaim Mathgamain Mahon bear Meadhran Medran " +
                "Meallán Mell n Mellan Mullin Medabh Mel Meldan Melkorka Melrone Meubred " +
                "Midir Midhir Mider Mirin Mo-Bioc Mochoemoc Mochta Mochteus Mochua Mochumma " +
                "Modomnoc Mogue Molling Moloi Molua Moluag More Morna Muchin Mughran " +
                "Muirghean Muirgheas Muirgius Muiris Muiriartach Muicheachtach Muireadhach " +
                "Muirchertach Muiredach Muiríoch Murtagh Murty Briartach Muirí Munnu Mura " +
                "Muranus Murchad Murchadh Murcha Murrough Naoise Nise Nathi Nemid Nevan " +
                "Niadh Niall Neal Neill Neil Ninian Notal Nuallán Odhrán Odran Oran Odhar " +
                "Ógán Oisín Oissne Ossian Oisin Osheen Oscar Otteran Ounam Phelan Piran " +
                "Rádhulbh Raghallach Reilly Riley Riaghan Riogh Rónán Ronan Rownan Rórdán " +
                "Riordan Reardan Rearden Ríoghbhardán Ros Ró Adhán Róad Rhod Rodan Ruan " +
                "Rowan Ruaidhrí Ruaidrí Ruairí Rudraighe Roricus Ruidhe Saebhreathach " +
                "Sáerbrethach Saorbhreathach Saoirse Scelianus Scolaidh Scolaighe Sé " +
                "Seachnall Séafra Seafraid Séaghdha Ségdae Seanán Senan Seanach Senach " +
                "Séarlan Sedna Seaghán Segenus Sheary Shiel Siadhal Siaghal Siochfioldha " +
                "Sinon Siran Siseal Sól Starn Steimhin Suibhne Suibne Sweeney Sivney Suthan " +
                "Tadhg Tadg Tadc Tegue Teigue Teige Taig Taidgh Tiege Taidhgín Tathai " +
                "Tiamhdha Tighearnach Tighernach Tiarnach Tiarna Tierney Tighearnán Tigern " +
                "Tiarnán Tiernan Tiomóid Toirdhealbhach Toirdhealbharch Tairdelbach " +
                "Toirealach Tárlach Tirloch Traolach Turlough Tomaltach Torrianus Treon " +
                "Tóathal Tuathal Toal Uaithne Uallachán Uar Uileos Uillen Úistean Ultán " +
                "Ultan an Ulsterman Urthaile Usliu Uthmaran Vigean Wyllow Aberth Abhartach " +
                "Abhean Ablach Acaunus Acco Adcoprovatus Addedomarus Adgennus Adhna " +
                "Adhnuall Adminius Adomnan Adwen Aedan Aelchinn Aer Aesico Aesk Aesubilinus " +
                "Agh Agned Agnoman Agulus Aherne Aiel Ailbhe Ailell Ailill Aillen Aillinn " +
                "Aincel Aindelbadh Aine Ainle Ainmire Ainsel Ainvar Airard Airetach " +
                "Airnelach Airt Albanach Albarnaid Alcuin Allobrogicus Alpin Alston Aluin " +
                "Alun Amalgoid Ambicatos Ambiorix Amergin Amgerit Amlaibh Amulgo Andala " +
                "Andela Andesasus Andragius Androgius Aneroestus Anlaf Antedios Aouen " +
                "Apullio Arbell Arcallach Archil Archu Ardan Argentocoxos Argentocoxus " +
                "Arias Ariomardus Ariovistus Arontuis Arranen Art Artbranan Artgal Arthgal " +
                "Arthmail Artigan Artrach Artri Arverus Arviragus Asal Ascatinius Atepacius " +
                "Attus Audagus Auisle Aulay Aurog Autaritus Avitus Bacauda Baclan Baculo " +
                "Badvoc Baethbarr Baiscne Baithan Baithen Baithene Balor Banquerius Banquo " +
                "Barloc Barnoc Baroc Baruch Bathan Beag Becuma Bedwyr Belatucader Bellicia " +
                "Bellicianus Bellovesus Belu Beolain Berchan Berec Beric Bericus Bernech " +
                "Berngal Berric Bersa Betach Bhuice Bicelmos Bilis Biorach Bitucus " +
                "Bitudacus Blaan Blathmac Blathmec Bleddfach Blescius Bloc Boann Boant Bobd " +
                "Bodenius Bodh Bodhe Bodiccius Bodugenus Boduogenus Bodvoc Bodvogenus " +
                "Bogitarus Boisel Boisil Bonoxus Borba Bothan Bov Brach Bragon Bran Brancus " +
                "Brannoc Brath Breagan Breasal Brecbrennoch Brelade Brennus Breogan Bres " +
                "Bresal Breward Briavel Bricriu Bricrue Brieuc Brigaco Brigantius Brigia " +
                "Briginus Brigomaglos Brioc Britt Broccan Brockmail Broderick Brogus " +
                "Broichan Brucetius Brude Bruide Bruidge Bruscius Brychanus Brys Bryth Buan " +
                "Buccus Buda Budocesuganios Buic Buichet Buite Cabrach Cabriabanus " +
                "Cacumattus Cadwan Caedmac Cael Caenneth Caibre Caichear Caier Cailcheir " +
                "Caince Caincenn Cainnelscaith Cainte Cairbre Cairbri Cais Caisel Caitchenn " +
                "Caittil Calgacus Calphurn Camel Camulacus Candiedo Cannaid Caoilte Capell " +
                "Caractacus Caradig Caratach Carbery Carbh Carell Carpre Cartivellaunos " +
                "Carvilius Cas Cascorach Cassal Cassavus Cassivellaunus Cassobellaunus " +
                "Catavignus Cathail Cathal Cathan Cathba Cathbadh Cathlan Cathman Catigern " +
                "Catiotuos Cattigern Catualda Cavarinus Cé Cealaigh Ceallach Ceanatis " +
                "Ceannmhair Cearnach Cecht Ceithin Celatus Cellach Celtchar Celtillus Cenau " +
                "Cerball Cerd Cerebig Ceretic Cermait Cerotus Cesarn Cet Cethern Cett " +
                "Ciabhan Ciach Cian Cicht Cimarus Cinaed Cingetorix Cinhil Cintugnatus " +
                "Cintusmus Ciotha Ciothruadh Cistumucus Cithruadh Clanova Cliach Clonard " +
                "Cluim Cobthach Cochlan Codal Codhna Coemgen Cogidubnos Cogidubnus " +
                "Cogitosus Coimhleathan Coinmagil Coinmail Coinneach Coirpre Colasunius " +
                "Colban Colga Coll Colla Collamair Collbrain Colles Colm Colmkill Colpa " +
                "Colum Comgal Comgall Comgan Comhrag Comitinus Comman Commius Compar " +
                "Comrith Comur Comux. Con Conaing Conaire Conairy Conal Conall Conan " +
                "Conaran Concolitanus Conconnetodumnos Concuing Condidan Conmail " +
                "Connachtach Connell Connla Connor Conor Conory Conuall Copillus Coplait " +
                "Coran Corann Corb Corc Corfil Corin Corio Cormiac Coron Corotiacus Corpry " +
                "Corrgenn Cospatrick Costicus Cotuatus Covac Craftiny Credne Crega Crico " +
                "Cridenbel Crimall Crimthan Crimthann Criomnal Crocus Crom Crotus Crovan " +
                "Cruithne Crunnagh Crunnchu Cu Cuadan Cuailgne Cuaillemech Cualann Cuano " +
                "Cuchulainn Cuiline Cuill Cuiran Cuirithir Culain Culcaigrie Culhwch Cullen " +
                "Cumhaill Cumhal Cumhall Cummain Cuneda Cunedda Cuneglasus Cunetio Cunittus " +
                "Cunlinc Cunoarda Cunobarros Cunobarrus Cunobelin Cunobelinus Cunomaglus " +
                "Cunopectus Cunori Cunorix Cunotamus Cunoval Cunovindus Cur Curatio " +
                "Curmissus Curoi Cushling Cuthlyn Cynloyp Cynran Cyrnan Dagobitus Daich " +
                "Daighre Daigre Daire Dalbaech Dalbh Dall Dannicus Dathi Deaghadh Dearc " +
                "Dearmid Debrann Decheall Dedidach Deglain Delbaith Demna Derc Derca " +
                "Dergcroche Dergdian Dering Desa Detha Dian Dian- Cet Diarmaid Diarmait " +
                "Diarmid Dichu Digbail Dill Dinogad Diocain Diorraing Diovicus Diviciacus " +
                "Dobar Doccius Dogfael Doinus Dolar Dolb Doli Domhar Domingart Domnann " +
                "Domnoellaunus Donnarthadh Donn- Ruadh Dornoch Dorus Drecan Drem Dremen " +
                "Driccius Driumne Drochmail Drostan Druim Druimderg Drust Drustic Drystan " +
                "Duach Duane Duartane Duatha Dubh Dubhacon Dubhan Dubhdaleithe Dubhgall " +
                "Dubhlaing Dubnovellaunus Dubnowalos Dubnus Dudoc Dufan Dufgal Duftah " +
                "Dugald Dumnail Dumnocoverus Dumnogenus Dumnorix Dumnove Dumnovellaunus " +
                "Dunegal Dunegall Dunmail Dunocratis Dunod Duthac Eab Eachaidh Easal " +
                "Eathfaigh Eber Ebicatos Eborius Eburos Echaid Ecimius Ecne Eidirsgul " +
                "Eimhir Eine Eisu. Eithis Elagabalus Elaphius Elatha Elathan Elbodugus " +
                "Elcmair Eldad Elitovius Elkmar Elvod Elvodug Eman Emi Emmass Enchered Enda " +
                "Enemnogenus Enestinus Eoban Eochaid Eocho Eochy Eochymac Eogabil Eogan " +
                "Eoganan Eoghan Eolus Eparchius Epaticcu Epaticcusepatticus Epillicus " +
                "Eppillus Erc Eremon Erp Err Ervic Esca Espaid Esunertus Etain Etar " +
                "Etarlaim Eterskel Etgall Ethain Ethaman Eunan Evicatos Facha- Muilleathan " +
                "Faelinn Faltlaba Faolan Farinmagil Farinmail Febal Feclach Fedlimidh " +
                "Feidlim Feinn Felim Ferai Fercos Ferdia Ferdiad Ferdoman Fermaise Fertai " +
                "Fertuinne Festinien Ffion Fiacha Fiachna Fiachu Fiacuil Ficna Figel Figol " +
                "Finan Finched Findabair Findemas Findgoll Findlaech Finegas Finn " +
                "Finnaistucan Finnan Finnbane Finnbennach Finnian Finnleik Fintain Finvel " +
                "Fiodhaidh Fitheal Flain Flann Flannan Flidias Fochlann Foich Foilleán " +
                "Foiranach Fola Follamain Forannán Forannen Forne Fothaid Fotla Fuad " +
                "Fufidius Fullon Fursa Gaible Gallgoid Gamal Garad Garbhcronan Garraidh " +
                "Gartnait Garva Garwin Garym Gault Gavin Gebann Germocus Geron Getorix " +
                "Gilla Gillaciaran Gillacomghain Gillechrist Gillibride Gillicolm Gillocher " +
                "Gilloman Gingomarus Giolla- Caeimhghin Glas Glasan Glein Glentilt Glore " +
                "Gnathach Gnobeg Gnomor Gobannitio Gobhan Godebog Goden Godfraidh Goineach " +
                "Goitne Goll Gorm-Shuileach Gorthyn Gospatrick Gothan Gourchien Govan " +
                "Graeme Graham Grannus Gretorix Grian Grummoch Guan Guern Gugein Guitolinus " +
                "Guoruoe Guotepauc Guthar Guthor Gwythno Hanesa Hanlon Hanno Heber Heremon " +
                "Huil Hunno Idanach Iduthin Iehmarc Igalram Ilar Ilaunos Ilbrec Ilbrech " +
                "Ildathach Imhar Imidd Indech Indrechtach Indutioamrus Indutius Ingcel " +
                "Ingnathach Ingol Innel Innsa Invomandus Iogenan Iollen Iolunn Ir Irdun " +
                "Istolatius Istoreth Ith Iubdan Iuchar Iucharba Iunsa Ivomagus Ivonercus " +
                "Jutus Kane Keir Kentigern Kenulphus Kian Kilian Kinan Kinemark Kineth " +
                "Kinnear Kolbein Kuno Kylan Kyndylan Labra Labran Lachlan Laeg Laegaire " +
                "Laeghairé Laery Laethrig Lainbhui Lairgnen Lairgren Laisren Lanuccus " +
                "Latharne Leffius Leire Leith Leslie Lethan Levin Lewy Lia Liagan Liath " +
                "Liathain Liathan Lifecar Lindores Liobhan Lir Litaviccus Litugenus Llif " +
                "Llud Loarn Loarne Lobais Loban Lobharan Lobos Logiore Logotorix Lomna Lon " +
                "Lorcain Lossio Lousius Lovernianus Luachaid Luachair Lubrin Lucco Luchta " +
                "Luchtar Luctacus Luel Luga Lugaidh Lughaid Lugobelinus Lugotorix Lugovalos " +
                "Luibra Luloecen Lyfing Lynch Mac Macbeathach Macbeathad Macer Machar " +
                "Machute Macnia Madach Madan Maddan Madduin Mador Maeduin Mael Maelbeth " +
                "Maelchwn Maeldun Maelgan Maelgwn Maelinmhain Maelmadoc Maelmichael " +
                "Maelmuir Maelmuire Maelnibha Maelochtair Maelochtar Maelrubai Maelrubha " +
                "Maelsechlainn Maelsechnaill Maeltine Maglocunus Maglorix Maieul Mailchon " +
                "Maine Mal Malbride Maldred Malduin Malliacus Malone Malpedar Malpedur " +
                "Malride Mamos Mandubracius Manducios Mannig Maol Maon Mar Marbod Maredoc " +
                "Marobodunus Martacus Maslorius Mathgen Matuacus Matugenus Maturus Meardha " +
                "Meargach Mechi Medraut Mellonus Melmor Menua Merddyn Mhaolain Mhichil " +
                "Miach Michan Midac Mide Midhé Midhir Midhna Midir Mil Miled Milucra " +
                "Miochaoin Miodac Miorog Mochrum Mochta Mochuda Modhaarn Modomnoc Moengal " +
                "Molacus Molaise Molloy Moluag Monaid Moncuxoma Mongan Morann Morc Morgund " +
                "Moriartak Morias Moricamulus Morirex Moritasgus Morvidd Motius Muadhan " +
                "Muddan Muirchú Muiredach Mungo Murchadh Mutaten Muthill Nadfraech Naid " +
                "Nantosvelta Nantua Naoise Narlos Natanleod Nathrach Natorus Neamh " +
                "Necalimes Nechtan Nectovelius Neidhe Neit Nemanach Nemed Nemglan Nemhnain " +
                "Nemmonius Nessa Niadhnair Nollaig Nos Novantico Nuada Nuadha Nynia " +
                "Octrialach Octruil Odhrain Odras Ogma Ogmios Oilioll Oisin Olchobar " +
                "Ollovico Oncus Orbissa Oren Orgetorix Orgillus Orphir Oscair Oscar Osgar " +
                "Owain Paetus Patendinus Pesrut Pisear Potitus Potomarus Prasutagus " +
                "Pridfirth Qodvoldeus Raighne Raigre Rascua Regol Reoda Reo-Derg Rhiada " +
                "Riagall Rian Rianorix Riata Ribh Ringan Riommar Rivius Robartaig " +
                "Robhartach Rogh Roth Rowan Ruadan Ruadhan Rudrach Rudraighe Ruide Ruith " +
                "Rurio Saccius Saenius Saenu Saidhe Sal Salmhor Salorch Saltran Samtan " +
                "Samthainn Sangus Saturio Sawan Sceolan Scrocmagil Scrocmail Seaghan " +
                "Seanchab Searigillus Searix Sechnaill Secumos Sedullus Seghine Segine " +
                "Segovax Sellic Semion Senaculus Sencha Senias Sennianus Senorix Senshenn " +
                "Senuacus Sepenestus Sera Servan Sesnan Setanta Setibogius Sgoith- Gleigeil " +
                "Sharvan Sholto Sighi Sigmal Silinus Sinell Sinill Sinnoch Sinsar Sital " +
                "Sitric Skolawn Sligech Smertrius Solais Sollus Sorio Soulinus Sreng " +
                "Stariat Starn Stavacus Strathairn Strowan Struan Suadnus Sualdam Sualtam " +
                "Suanach Suavis Subhkillede Subsio Sucabus Suibhne Sulien Summacus Suriacus " +
                "Syagris Tabarn Tadg Tailc Taileach Taistellach Talchimen Taliesin Talorcan " +
                "Talore Tamesubugus Tammonius Tarvos Tasciovanus Tasgetius Tassach " +
                "Taximagulus Tethra Tetrecus Tetricus Teutomatus Teyrnon Tigernach " +
                "Tigernann Tincomarus Tincommius Tocha Togodumnus Topa Tor Torannen Toutius " +
                "Trad Tradui Trendhorn Trenmor Trenus Treon Triathmor Trogain Troghwen " +
                "Tuaigh Tuan Tuathal Tuirbe Tuireann Tuis Tullich Turenn Turlough Tyrnon " +
                "Uaithnin Uar Uccus Uchtain Ueda Uepogenus Uige Uirolec Uisneach Ulchil Un " +
                "Unthaus Urfai Urgriu Urias Urien Usna Usnach Vadrex Vainche Vallaunius " +
                "Vassedo Vatiaucus Veda Vediacus Vellocatus Veluvius Venutius Vepogenus " +
                "Vercassivellaunus Vercingetorix Verctissa Verecundus Verica Vernico " +
                "Viasudrus Viducus Vindex Vindomorucius Vinnian Virdomarus Viroma " +
                "Virssucius Volisus Vortigern Vortimax Vortimer Vortipor Vortrix Voteporix " +
                "Vran Vron Wannard Weonard ";

        String[] ary = allNames.split(" ");
        String firstName = ary[randInt.GetRandInt(0, ary.length - 1)];
        return "" + ary[randInt.GetRandInt(0, ary.length - 1)] + " " + ary[randInt.GetRandInt(0, ary.length - 1)];
    }

}
