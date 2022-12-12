export default function Instructions() {
  return (
    <div className="sidebar">
      <h1>TopBloc Coding Challenge</h1>
      <p>
        An engineer has created a web application for Randy's Candies, a local
        candy store in Chicago. Randy, the owner, asked that the application to
        does three things:
        <ol>
          <li>
            Display items that are almost out of stock (at less than 25%
            capacity)
          </li>
          <li>
            Allow him to input how many of each of those items he wants to
            re-order
          </li>
          <li>Determine the lowest total cost of re-ordering those items</li>
        </ol>
        The web application consists of a Java backend and React frontend.
        <p>
          <b>
            We need you to write unit tests for this web application that maximize
            test coverage. Feel free to make any changes to the code that you
            see fit. You may also add other types of tests other than unit tests if you'd like.
          </b>
        </p>
        <h3>Backend</h3>
        <h4>Workbooks</h4>
        The backend contains two workbooks, <code>Inventory.xlsx</code> and{" "}
        <code>Distributors.xlsx</code>.<code>Inventory.xlsx</code> contains the
        items in the store: their SKU, name, amount in stock, and the capacity
        in the store for that item. <code>Distributors.xlsx</code> contains
        three worksheets, one for each distributor, which list the items the
        distributor sells: their SKU, item name, and price. Note that each
        distributor will not necessarily have all the items that Randy sells at
        his store. <h4>Endpoints</h4>
        The backend exposes two endpoints at <code>
          http://localhost:4567
        </code>{" "}
        using the <a href="https://sparkjava.com/">Java Spark</a> library:{" "}
        <code>/low-stock</code> and <code>/restock-cost</code>. The{" "}
        <code>/low-stock</code> endpoint should return only the items that are
        at less than 25% capacity. The <code>/restock-cost</code> endpoint
        should return the lowest total cost for Randy to restock. The lowest
        total cost is determined by the lowest available price across different
        distributors. The items to restock and their amounts should be provided
        in the request body from the frontend.
      </p>
      <h3>Frontend</h3>
      <p>
        The frontend will be responsible for utilizing the Java endpoints to
        display the low-stock items and calculating the cost of restocking
        items. You will need to add the appropriate event listeners to the
        buttons for using these endpoints.
      </p>
    </div>
  );
}
