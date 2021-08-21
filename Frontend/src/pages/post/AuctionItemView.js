import React, { useEffect, useState } from 'react';
// import { getPostByNo } from '../../Data';
import baseUrl from 'constants'
import './auctionItem.css';
import axios from "axios";

const AuctionItemView = ({ history, location, match }) => {
  const [data, setAuctionItem] = useState([]);
  const id = match.params.id;

  async function getAuctionItemById(id){
    await axios
    .get(baseUrl + "/auctionItem/" + id)
    .then((response) => {
      setAuctionItem(response.data);
    })
    .catch((error) => {
      console.error(error)
    })
  }

  useEffect(() => {
    setAuctionItem(getAuctionItemById(id));
  }, [ ]);

  return (
    <>
      <h2 align="center">경매 물품 상세 정보</h2>

      <div className="post-view-wrapper">
        {
          data ? (
            <>
              <div className="post-view-row">
                <label>등록 번호</label>
                <label>{ data.id }</label>
              </div>
              <div className="post-view-row">
                <label>제목</label>
                <label>{ data.title }</label>
              </div>
              <div className="post-view-row">
                <label>등록 일자</label>
                <label>{ data.createDate }</label>
              </div>
              <div className="post-view-row">
                <label>소유자</label>
                <label>{ data.owner }</label>
              </div>
              <div className="post-view-row">
                <label>내용</label>
                <div>
                  {
                    data.description
                  }
                </div>
              </div>
              <div className="post-view-row">
                <label>시작 가격</label>
                <label>{ Number(data.startPrice).toLocaleString('ko-KR') + " 원" }</label>
              </div>
              <div className="post-view-row">
                <label>호가</label>
                <label>{ Number(data.biddingPrice).toLocaleString('ko-KR') + " 원" }</label>
              </div>
            </>
          ) : '해당 게시글을 찾을 수 없습니다.'
        }
        <button className="post-view-go-list-btn" onClick={() => history.goBack()}>목록으로 돌아가기</button>
      </div>
    </>
  )
}

export default AuctionItemView;