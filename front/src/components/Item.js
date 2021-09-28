const Item = ({ item }) => {
    return (
        <div className='item'>
            <h3>{item.title}</h3>
            <ul>
                <li>시작가: {item.initialPrice}</li>
                <li>설명: {item.description}</li>
                <li>등록일: {item.registeredDate}</li>
            </ul>
        </div>
    )
}

export default Item
