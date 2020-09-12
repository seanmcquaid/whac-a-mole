import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from 'react-router-dom';
import styled from 'styled-components';
import P from '../../sharedComponents/universal/Typography/P';
import { endGame, incrementTimer } from '../../store/game/actions';
import {
  gameActiveSelector,
  molesLeftSelector,
  totalTimeSelector,
} from '../../store/game/selectors';
import GameBoard from './GameBoard';

const MainGame: React.FC = () => {
  const molesLeft = useSelector(molesLeftSelector);
  const totalTime = useSelector(totalTimeSelector);
  const dispatch = useDispatch();
  const history = useHistory();

  useEffect(() => {
    if (molesLeft !== 0) {
      const timer = setInterval(() => {
        dispatch(incrementTimer());
      }, 1000);

      return () => {
        clearInterval(timer);
      };
    } else {
      dispatch(endGame());
      history.push('/scoreStatus');
    }
  }, [dispatch, molesLeft, history]);

  return (
    <GameContainer>
      <P>Total Time : {totalTime}</P>
      <P>Moles Left : {molesLeft}</P>
      <GameBoard />
    </GameContainer>
  );
};

const GameContainer = styled.div``;

export default MainGame;