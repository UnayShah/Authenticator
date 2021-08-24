package com.UnayShah.Authenticator.service;

import com.UnayShah.Authenticator.dao.Website;
import com.UnayShah.Authenticator.repository.WebsiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteService {
    @Autowired
    WebsiteRepository websiteRepository;

    public Website addWebsite(String websiteDisplayName, String websiteRedirectURL) {
        return websiteRepository.save(new Website(websiteDisplayName, websiteRedirectURL));
    }

    public Website findWebsite(String websiteId) {
        return websiteRepository.existsById(websiteId) ? websiteRepository.findById(websiteId).get() : null;
    }

    public Boolean editWebsiteDisplayName(String websiteId, String websiteDisplayName, String newWebsiteDisplayName) {
        Website website = findWebsite(websiteId);
        if (website.getWebsiteDisplayName().equals(websiteDisplayName)) {
            website.setWebsiteDisplayName(newWebsiteDisplayName);
            websiteRepository.save(website);
            return true;
        }
        return false;
    }

    public Boolean editWebsiteRedirectURL(String websiteId, String websiteRedirectURL, String newWebsiteRedirectURL) {
        Website website = findWebsite(websiteId);
        if (website.getWebsiteRedirectURL().equals(websiteRedirectURL)) {
            website.setWebsiteRedirectURL(newWebsiteRedirectURL);
            websiteRepository.save(website);
            return true;
        }
        return false;
    }

    public Boolean removeWebsite(String websiteId, String websiteDisplayName, String websiteRedirectURL) {
        Website website = findWebsite(websiteId);
        if (website != null && website.getWebsiteDisplayName().equals(websiteDisplayName)
                && website.getWebsiteRedirectURL().equals(websiteRedirectURL)) {
            websiteRepository.deleteById(websiteId);
            return true;
        }
        return false;
    }
}
