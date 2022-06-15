package model.sites;

import model.APISetUp;
import model.EntityManager;
import model.adapters.JsonToSiteAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

/**
 * Manages the testing sites within the system using the singleton pattern
 */
public class SitesManager extends EntityManager {


    private ArrayList<Site> allSites;
    private static SitesManager INSTANCE;

    /**
     * Singleton constructor to construct a single instance of the SitesManager
     * @throws Exception if the API response is invalid
     */
    private SitesManager() throws Exception {
        this.url = APISetUp.getUrlBase() + "testing-site";
        this.allSites = this.findAllEntities();
    }

    /**
     * Ensures a single sintance is present in the system
     * @return The instance of the SitesManager in the system or a new one if there isn't already an instance
     * @throws Exception if the API response is invalid
     */
    public static SitesManager getInstance() throws Exception {
        if (INSTANCE == null){
            INSTANCE = new SitesManager();
        }
        return INSTANCE;
    }


    /**
     * Allows for search by testing site
     * @param siteType string describing the site type eg "on site" or "drive through"
     * @return The list of sites fitting the type input
     * @throws Exception if the API response is invalid
     */
    public ArrayList<Site> getSitesBySiteType(String siteType) throws Exception {
        ArrayList<Site> res = new ArrayList<>();
        if (allSites == null){
            this.allSites = findAllEntities();
        }
        for (Site site : allSites){
            for (JsonNode type : site.getSiteTypes()){
                if (type.toString().replaceAll("^\"|\"$", "").equals(siteType)){
                    res.add(site);
                }
            }
        }
        return res;
    }

    /**
     * Allows for search by test type
     * @param testType string describing the site type eg "PRC" or "RAT"
     * @return The list of sites fitting the type input
     * @throws Exception if the API response is invalid
     */
    public ArrayList<Site> getSitesByTestType(String testType) throws Exception {
        ArrayList<Site> res = new ArrayList<>();
        if (allSites == null){
            this.allSites = findAllEntities();
        }
        for (Site site : allSites){
            for (JsonNode type : site.getTestTypes()){
                if (type.toString().replaceAll("^\"|\"$", "").equals(testType)){
                    res.add(site);
                }
            }
        }
        return res;
    }

    /**
     * Allows for search by suburb
     * @param suburb string describing the site type eg "Clayton" or "Mount Waverley"
     * @return The list of sites fitting the type input
     * @throws Exception if the API response is invalid
     */
    public ArrayList<Site> getSitesBySuburb(String suburb) throws Exception {
        ArrayList<Site> res = new ArrayList<>();
        if (allSites == null){
            this.allSites = findAllEntities();
        }
        for (Site site : allSites){
            String siteSuburb = site.getSuburb();
            if (siteSuburb.replaceAll("^\"|\"$", "").equals(suburb)) {
                res.add(site);
            }
        }
        return res;
    }

    /**
     * Contains logic to add sites to the manager's management
     * @param jsonNodes API response data corresponding to the sites to be added
     * @return The list of sites under the manager's management
     */
    @Override
    protected ArrayList addEntities(ObjectNode[] jsonNodes) {
        ArrayList entities = new ArrayList();
        for (ObjectNode node : jsonNodes){
            Site site = new Site(new JsonToSiteAdapter(node));
            entities.add(site);
        }
        return entities;
    }
}
