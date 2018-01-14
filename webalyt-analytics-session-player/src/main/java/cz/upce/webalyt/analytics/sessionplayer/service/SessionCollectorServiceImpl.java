package cz.upce.webalyt.analytics.sessionplayer.service;

import cz.upce.webalyt.analytics.sessionplayertest.repository.*;
import cz.upce.webalyt.plugin.core.WebalytEntity;
import org.apache.commons.collections4.list.CursorableLinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SessionCollectorServiceImpl implements SessionCollectorService {

    @Autowired
    private ClickRepository clickRepository;

    @Autowired
    private MousePositionRepository mousePositionRepository;

    @Autowired
    private RecordedDomRepository recordedDomRepository;

    @Autowired
    private ScrollTrackerRepository scrollTrackerRepository;

    @Autowired
    private RecordedSizeRepository recordedSizeRepository;

    @Override
    public List<WebalytEntity> getByPageViewId(String pageViewId) {
        List<WebalytEntity> webalytEntities = new CursorableLinkedList<>();
        webalytEntities.addAll(clickRepository.findByPageViewId(pageViewId));
        webalytEntities.addAll(mousePositionRepository.findByPageViewId(pageViewId));
        webalytEntities.addAll(recordedDomRepository.findByPageViewId(pageViewId));
        webalytEntities.addAll(scrollTrackerRepository.findByPageViewId(pageViewId));
        webalytEntities.addAll(recordedSizeRepository.findByPageViewId(pageViewId));
        Collections.sort(webalytEntities, (o1, o2) -> {
            if(o1.getTimestamp().after(o2.getTimestamp())) {
                return 1;
            } else if (o1.getTimestamp().before(o2.getTimestamp())){
                return -1;
            } else {
                return 0;
            }
        });
        return webalytEntities;
    }
}
