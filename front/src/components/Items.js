import Item from "./Item"

const Items = ({ items }) => {
    return (
        <>
            {items.map((item) => (
                <Item item={item} />
            ))}
        </>
    )
}

export default Items
