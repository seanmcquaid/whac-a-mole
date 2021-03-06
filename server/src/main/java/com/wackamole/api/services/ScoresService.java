package com.wackamole.api.services;

import com.wackamole.api.dao.ScoresDao;
import com.wackamole.api.models.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ScoresService {
    private final ScoresDao scoresDao;

    @Autowired
    public ScoresService(ScoresDao scoresDao) {
        this.scoresDao = scoresDao;
    }

    public boolean isScoreInTopTen(int time){
        List<Score> topTenScores = scoresDao.getTopTenScores();
        System.out.println(topTenScores);
        System.out.println(time);

        if(topTenScores.size() == 0 || topTenScores.size() < 10){
            return true;
        }

        AtomicBoolean isScoreInTopTen = new AtomicBoolean(false);

        topTenScores.forEach(score -> {
            if(time < score.getScore()){
                isScoreInTopTen.set(true);
            }
        });

        return isScoreInTopTen.get();
    }

    public List<Score> getTopTenScores(){
        return scoresDao.getTopTenScores();
    }

    public void addScore(Score score){
        scoresDao.addScore(score);
    }

    public List<Score> getScoreForPlayerName(String playerName){
        return scoresDao.getScoreForPlayerName(playerName);
    }
}
