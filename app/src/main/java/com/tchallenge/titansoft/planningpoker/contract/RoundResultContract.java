package com.tchallenge.titansoft.planningpoker.contract;

import java.util.Map;

public class RoundResultContract {
    public interface IRoundResultView {
        void onMemberInfosUpdate(Map<String, String> memberInfos);
    }

    public interface IRoundResultPresenter {
        void fetchMemberInfos();
    }
}
