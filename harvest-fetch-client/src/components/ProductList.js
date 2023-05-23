import ProductCardSearch from "./ProductCardSearch";

export default function Farmer(props) {

    const filteredData = props.filter((el) => {
        if (props.input === '') {
            return el;
        }

        else {
            return el.text.includes(props.input)
        }
    })

    return (
        <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-2">
            {products.map(p => <ProductCardSearch key={p.productId} product={p} input={inputText} />)}
        </div>
    )
}


