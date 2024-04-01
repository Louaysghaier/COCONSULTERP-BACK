package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.ProjFeed;

import java.util.List;

public interface ProjFeedService  {

    List<ProjFeed> retrieveProjFeeds();

    ProjFeed updateProjFeed(ProjFeed projFeed);

    ProjFeed addProjFeed(ProjFeed projFeed);

    ProjFeed retrieveProjFeed(Long idProjFeed);

    void removeProjFeed(Long idProjFeed);
}