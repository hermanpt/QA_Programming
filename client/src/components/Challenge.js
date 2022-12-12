import React, { useState } from "react";
import axios from "axios";
import ItemRow from "./ItemRow";


export default function Challenge() {
  const [lowStock, setLowStock] = useState({
    data: "",
    loading: true,
  });
  const [restockCost, setRestockCost] = useState([]);

  const handleClickLowStock = async () => {
    const data = await axios.get("http://localhost:4567/low-stock");
    setLowStock({
      data: data.data,
      loading: false,
    });
  };

    const handleReset = async () => {
        const data = await axios.get("http://localhost:4567/low-stock");
        setLowStock({
            data: data.data,
            loading: false,

        });
        setRestockCost(data.data);
    };


  const handleClickReorderCost = async () => {
    const data = await axios.post(
      "http://localhost:4567/restock-cost",
      lowStock.data
    );
    setRestockCost(data.data);
  };

  return (
    <>
      <table>
        <thead>
          <tr>
            <td>SKU</td>
            <td>Item Name</td>
            <td>Amount in Stock</td>
            <td>Capacity</td>
            <td>Order Amount</td>
          </tr>
        </thead>
        <tbody>
          {lowStock.loading
            ? ""
            : lowStock.data.map((item) => (
                <ItemRow
                  key={item.id}
                  item={item}
                  lowStock={lowStock}
                  setLowStock={setLowStock}
                />
              ))}
        </tbody>
      </table>
      <div class="totalCost">
        Total Cost: ${Math.round((restockCost + Number.EPSILON) * 100) / 100}
      </div>
      <button onClick={handleClickLowStock}>Get Low-Stock Items</button>
      <button onClick={handleClickReorderCost}>Determine Re-Order Cost</button>
      <button onClick={handleReset}>Reset</button>
      <button onClick={handleClickReorderCost}>Auto Refill</button>
      <button><a href='https://paypal.com'>Order Now</a></button>

    </>
  );
}
