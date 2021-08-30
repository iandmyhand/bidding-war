import React, { useState, useEffect } from 'react';
import CommonTable from '../../component/table/CommonTable';
import CommonTableColumn from '../../component/table/CommonTableColumn';
import CommonTableRow from '../../component/table/CommonTableRow';
import { fetchBiddingsByAuctionItem } from '../../api/auctionItem/auctionItem_api';
import { bidding } from '../../api/auctionItem/auctionItem_api';

const AuctionItemBiddingView = ({ history, location, match }) => {
  
    const [biddings, setBiddings] = useState([]);
    const id = match.params.id;

    const [inputs, setInputs] = useState({
        amount: ''
      });
    
    const { amount } = inputs;
    
    const onChange = (e) => {
        const { value, name } = e.target;
        setInputs({
          ...inputs, // 기존의 input 객체를 복사한 뒤
          [name]: value // name 키를 가진 값을 value 로 설정
        });
      }; 
    
      function registerBidding(e){
        e.preventDefault();
        const registerBidding = async () => {
          try{
            const response = await bidding({
                auctionItemId: id,
                userId: sessionStorage.getItem("user_id"),
                amount: amount
            })
    
            window.alert("입찰을 완료했습니다.")
            window.location.reload()
            
          } catch(error){
            if (error.response.status === 409){
                window.alert("요청 가격보다 큰 입찰 금액이 이미 존재하거나, 최소 주문 금액보다 작습니다.")
                window.location.reload()
            }
            else{
                window.alert('세션이 만료되었습니다. 다시 로그인 해주세요.')
                window.location.replace("/login");
            }

          }
          
        }
        registerBidding();
        console.log("아이템 추가됌.")
      }

    useEffect(() =>{
        getBiddings();
    }, [])
  
    async function getBiddings(){
      await fetchBiddingsByAuctionItem(id)
      .then((response) => {
        setBiddings(response.data)
      })
      .catch((error) => {
        window.alert(error)
      })
    }
  
    function formatDate(date) {
       var d = new Date(date), 
       month = '' + (d.getMonth() + 1),
       day = '' + d.getDate(), 
       year = d.getFullYear(); 
       
       if (month.length < 2) month = '0' + month; 
       if (day.length < 2) day = '0' + day; 
       
       return [year, month, day].join('-'); 
    }
  
    return (
      <>

        <form onSubmit={registerBidding}>
            <h2>
                <center>입찰 하기</center>
            </h2>
            <center><input name = "amount" type = "text" required = {true} value = {amount} onChange={onChange} placeholder="입찰가" /></center>
            &nbsp;&nbsp;
            <center><input type = "submit" value = "등록" /></center>
        </form>

        <CommonTable headersName={['입찰번호','유저', '입찰가', '요청시간']}>
          {
            biddings ? biddings.map((bidding) => {
              return (
                <CommonTableRow key={bidding.id}>
                  <CommonTableColumn>{ bidding.id }</CommonTableColumn>
                  <CommonTableColumn>{ bidding.user.email }</CommonTableColumn>
                  <CommonTableColumn>{ Number(bidding.amount).toLocaleString('ko-KR') + " 원" }</CommonTableColumn>
                  <CommonTableColumn>{ formatDate(bidding.createDate)}</CommonTableColumn>
                </CommonTableRow>
              )
            }) : ''
          }
        </CommonTable>


      </>
    )
}

export default AuctionItemBiddingView;

