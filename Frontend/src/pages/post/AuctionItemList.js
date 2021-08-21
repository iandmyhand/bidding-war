import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CommonTable from '../../component/table/CommonTable';
import CommonTableColumn from '../../component/table/CommonTableColumn';
import CommonTableRow from '../../component/table/CommonTableRow';
import { fetchAllAuctionItems } from '../../api/auctionItem/auctionItem_api';
const AuctionItemList = props => {
  const baseUrl = "http://localhost:8080/api"
  
  const [auctionItems, setAuctionItems] = useState([]);

  useEffect(() =>{
    getAuctions();
  }, [])

  async function getAuctions(){
    await fetchAllAuctionItems()
    .then((response) => {
      setAuctionItems(response.data)
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
      <CommonTable headersName={['등록번호', '제목', '소유자', '시작가격', '호가', '생성시간']}>
        {
          auctionItems ? auctionItems.map((auctionItem) => {
            return (
              <CommonTableRow key={auctionItem.id}>
                <CommonTableColumn>{ auctionItem.id }</CommonTableColumn>
                <CommonTableColumn>
                  <Link to={`/auctionItem/${auctionItem.id}`}>{ auctionItem.title }</Link>
                </CommonTableColumn>
                <CommonTableColumn>{ auctionItem.owner }</CommonTableColumn>
                <CommonTableColumn>{ Number(auctionItem.startPrice).toLocaleString('ko-KR') + " 원" }</CommonTableColumn>
                <CommonTableColumn>{ Number(auctionItem.biddingPrice).toLocaleString('ko-KR') + " 원"}</CommonTableColumn>
                <CommonTableColumn>{ formatDate(auctionItem.createDate)}</CommonTableColumn>
              </CommonTableRow>
            )
          }) : ''
        }
      </CommonTable>
    </>
  )
}

export default AuctionItemList;