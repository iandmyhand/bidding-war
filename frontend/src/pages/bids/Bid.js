import React, {useEffect, useState} from 'react';
import {fetchBids} from "../../api";
import styled from "styled-components";
import {Container} from '../../components/Login';
import CommonTable from '../../components/Table/CommonTable';
import CommonTableColumn from '../../components/Table/CommonTableColumn';
import CommonTableRow from '../../components/Table/CommonTableRow';

const Bids = () => {
    const [bids, setBids] = useState([])

    const initBids = async () => {
        const response = await fetchBids()
        console.log(response.data)

        setBids(response.data)
    }

    const BidButton = styled.div`
      font-size: 18px;
      font-weight: 700;
      line-height: 49px;
      display: block;
      width: 120px;
      height: 50px;
      margin-left: 45%;
      margin-top: 5%;
      margin-bottom: 5%;
      cursor: pointer;
      text-align: center;
      color: #fff;
      border-radius: 0;
      background-color: #03c75a;
    `;

    useEffect(() => {
        initBids().then()
    }, [])

    useEffect(() => {
    }, [bids])

    return <Container>
        <h1>Bidding-War</h1>
        <h2>입찰 목록</h2>
          <CommonTable headersName={['입찰번호', '제품번호', '유저ID', '입찰가']}>
            {
              bids ? bids.map((bid) => {
                return (
                  <CommonTableRow key={bid.id}>
                    <CommonTableColumn>{ bid.id }</CommonTableColumn>
                    <CommonTableColumn>{ bid.product_id }</CommonTableColumn>
                    <CommonTableColumn>{ bid.user_id }</CommonTableColumn>
                    <CommonTableColumn>{ bid.bidding_price }</CommonTableColumn>
                  </CommonTableRow>
                )
              }) : ''
            }
          </CommonTable>
    </Container>
}

export default Bids