import React, { useState, useEffect } from "react";
import axios from "axios";
import './App.css';

function App() {

  const baseUrl = "http://localhost:8080"

  const [inputs, setInputs] = useState({
    title: '',
    owner: '',
    description: '',
    startPrice: '',
    biddingPrice: ''
  });

  const { title, owner, description, startPrice, biddingPrice} = inputs;
  
  const [auctionItems, setAuctionItems] = useState([]);

  useEffect(() =>{
    getAuctions();
  }, [])

  async function getAuctions(){
    await axios
    .get(baseUrl + "/auctionItem")
    .then((response) => {
      setAuctionItems(response.data);
    })
    .catch((error) => {
      console.error(error)
    })
  }

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
              getAuctions();
            })
            .catch((error) =>{
              console.error(error);
            })
    }
    registerAuctionItem();
    console.log("아이템 추가됌.")
  }
  
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

  return (
    <div className="App" >
      <h1> PeopleFund 경매 사이트 </h1>
      <form onSubmit={registerAuctionItem}>
        경매 물품 등록
        <input name = "title" type = "text" required = {true} value = {title} onChange={onChange} placeholder="타이틀"/>
        <input name = "owner" type = "text" required = {true} value = {owner} onChange={onChange} placeholder="소유자"/>
        <input name = "description" type = "text" required = {true} value = {description} onChange={onChange} placeholder="설명" />
        <input name = "startPrice" type = "number" required = {true} value = {startPrice} onChange={onChange} placeholder="시작 가격"/>
        <input name = "biddingPrice" type = "number" required = {true} value = {biddingPrice} onChange={onChange} placeholder="호가" />
        <input type = "submit" value = "등록" />
      </form>
      <h2>올라온 경매 물품 목록</h2>
      {
        auctionItems
        ? auctionItems.map((auctionItem) => {
          return (
            <div className="auctionItem" key = {auctionItem.id}>
              <h3>
                <label onClick={null}>
                  {auctionItem.title} {auctionItem.owner} {auctionItem.description} {auctionItem.startPrice} {auctionItem.biddingPrice}
                </label>
              </h3>
            </div>
          )
        })
        :null
      }

    </div>
  );
}

export default App;
