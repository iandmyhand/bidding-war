import React, { useState } from 'react';
import AuctionItemList from './AuctionItemList';
import axios from "axios";


const AuctionMain = props => {
  const baseUrl = "http://localhost:8080/api"

  const [inputs, setInputs] = useState({
    title: '',
    owner: '',
    description: '',
    startPrice: '',
    biddingPrice: ''
  });

  const { title, owner, description, startPrice, biddingPrice} = inputs;

  const onReset = () => {
    setInputs({
      title: '',
      owner: '',
      description: '',
      startPrice: '',
      biddingPrice: ''
    })
  };

  const onChange = (e) => {
    const { value, name } = e.target;
    setInputs({
      ...inputs, // 기존의 input 객체를 복사한 뒤
      [name]: value // name 키를 가진 값을 value 로 설정
    });
  };

  function registerAuctionItem(e){
    e.preventDefault();

    let body = {
      title: title,
      owner: owner,
      description: description,
      startPrice: startPrice,
      biddingPrice: biddingPrice
    }
    const registerAuctionItem = async () => {
      await axios
            .post(baseUrl + "/auctionItem", body)
            .then((response) => {
              console.log(response.data)
              onReset();
              window.location.reload();
            })
            .catch((error) =>{
              console.error(error);
            })
    }
    registerAuctionItem();
    console.log("아이템 추가됌.")
  }

  return (
    <>
      <form onSubmit={registerAuctionItem}>
        <center>
        <img src = "https://static.peoplefund.co.kr/assets/img/pf_sns_banner.png?202106121719"  width = "500" height = "250"></img>
        </center>
        <h1>
          <center>피플펀드 경매 사이트</center>
        </h1>
          <h2>
            <center>물품 등록하기</center>
          </h2>
          <center><input name = "title" type = "text" required = {true} value = {title} onChange={onChange} placeholder="타이틀"/></center>
          &nbsp;&nbsp;
          <center><input name = "owner" type = "text" required = {true} value = {owner} onChange={onChange} placeholder="소유자"/></center>
          &nbsp;&nbsp;
          <center><input name = "description" type = "text" required = {true} value = {description} onChange={onChange} placeholder="설명" /></center>
          &nbsp;&nbsp;
          <center><input name = "startPrice" type = "number" required = {true} value = {startPrice} onChange={onChange} placeholder="시작 가격"/></center>
          &nbsp;&nbsp;
          <center><input name = "biddingPrice" type = "number" required = {true} value = {biddingPrice} onChange={onChange} placeholder="호가" /></center>
          &nbsp;&nbsp;
          <center><input type = "submit" value = "등록" /></center>
      </form>
      <h2 align="center">등록된 물품 목록들</h2>
      <AuctionItemList />
    </>
  )
}

export default AuctionMain;