<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8" />
<style>
.container {
	padding: 5px;
	background-color: lightblue;
}
</style>
</head>
<body>
	<div style="width: 600px; display: inline;">
		<div>
		<div class="container"
			style="width: 300px; height: 200px; float: left;">
			<h3>Home Page</h3>
			<div style="overflow-inline: auto;">
				<h5 th:text="${status}"></h5>
			</div>

		</div>
		<div style="position: absolute;margin-top: 220px;">
			<a href="/findLogs">Audit Logs</a>
			<table border="1">
				<thead>
					<tr>
						<th>Transaction type</th>
						<th>User</th>
						<th>Password</th>
					</tr>
				</thead>
				<tbody th:if="${session.auditLogs != null}">
					<tr th:if="${session.auditLogs.empty}">
						<td colspan="3">Audit Logs Empty</td>
					</tr>
					<tr th:each="aud : ${session.auditLogs}">
						<td align="left"><span th:text="${aud.trantype}"></span></td>
						<td align="left"><span th:text="${aud.user}"></span></td>
						<td align="left"><span th:text="${aud.password}"></span></td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
	<div style="float: left;">
		<a href="/findProduts">Product list</a>
		<form action="#" th:action="@{/addCart}" method="post">
			<table border="1">
				<thead>
					<tr>
						<th />
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody th:if="${session.products != null}">
					<tr th:if="${session.products.empty}">
						<td colspan="4">No Products Available</td>
					</tr>
					<tr th:each="product : ${session.products}">
						<td align="center"><input type="checkbox" name="pids"
							th:value="${product.id}" /></td>
						<td align="left"><span th:text="${product.name}"></span></td>
						<td align="left"><span th:text="${product.shortDescription}"></span></td>
						<td align="left"><span th:text="${product.mrp}"></span></td>
					</tr>
				</tbody>
			</table>
			<button type="submit">Add to Cart</button>
		</form>
	</div>
	<div style="float: left;">
		<span>Product in Cart</span>
		<form action="#" th:action="@{/orderCart}" method="post">
		<table border="1">
			<thead>
				<tr>
					<th />
					<th>Description</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody th:if="${carts != null}">
				<tr th:if="${carts.empty}">
					<td colspan="3">Cart Empty</td>
				</tr>
				<tr th:each="cart : ${carts}">
					<td align="center"><input type="checkbox" name="cids"
						th:value="${cart.id}" /></td>
					<td align="left"><span th:text="${cart.productName}"></span></td>
					<td align="left"><span th:text="${cart.price}"></span></td>
				</tr>
			</tbody>
		</table>
		<button type="submit">Submit Order</button>
		</form>
	</div>
	<div style="float: left;">
		<a href="/orderList">Order list</a>
		<table border="1">
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Description</th>
					<th>Delivery Date</th>
				</tr>
			</thead>
			<tbody th:if="${orderList != null}">
				<tr th:if="${orderList.empty}">
					<td colspan="3">No Order Placed</td>
				</tr>
				<tr th:each="order : ${orderList}">
					<td align="left"><span th:text="${order.orderId}"></span></td>
					<td align="left"><span th:text="${order.productName}"></span></td>
					<td align="left"><span th:text="${order.deliveryDate}"></span></td>
				</tr>
			</tbody>
		</table>
	</div>
	</div>

</body>

</html>