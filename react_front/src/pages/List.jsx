import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function List() {

    // メンバー、役職、事業所のデータを受け取る
    const [members, setMembers] = useState([]);
    const [positions, setPositions] = useState([]);
    const [places, setPlaces] = useState([]);

    // ソート情報を定義
    const [sortField, setSortField] = useState("memberId");
    const [sortDirection, setSortDirection] = useState("asc");

    // 画面遷移する関数を生成
    const navigate = useNavigate();

    // 起動後すぐに実行
    useEffect(() => {

        // 起動後すぐspringbootにつなぎ、オブジェクトを受け取る
        fetch(`http://localhost:8080/members/list?sortField=${sortField}&sortDirection=${sortDirection}`)
            .then(response => response.json())
            .then(data => {
                setMembers(data.members);
                setPositions(data.positions);
                setPlaces(data.places);
            })

        // タイトルをindex.htmlに渡す
        document.title = "メンバー一覧";
        // sortField、sortDirectionが変更するごとに実行
    }, [sortField, sortDirection]);

    // メニュー画面遷移
    const handleMenu = () => {
        navigate('/');
    }

    // 詳細画面へ遷移
    const handleDetail = () => {
        navigate('/detail');
    }

    // 更新画面へ遷移
    const handleUpdate = () => {
        navigate('/update');
    }

    // 削除画面へ遷移
    const handleDelete = () => {
        navigate('/delete');
    }

    // 表示
    return (
        <div id="wrapper">
			<div className="menu">
				<a onClick = { handleMenu }>メニュー</a><br />
			</div>
			<div className="menu">
				<h2>メンバ一覧画面</h2>
			</div>
			<table className="list">
				<thead>
					<th scope="col">
						<a onClick = {() => {
                            setSortField("memberId");
                            setSortDirection(sortField == "memberId" && sortDirection == "asc"
                                ? "desc"
                                : "asc"
                            );
                        }}>社員ID{sortField == "memberId" && (sortDirection == "asc" ? "↓" : "↑")}</a>
					</th>
					<th scope="col">
                    <a onClick = {() => {
                            setSortField("memberName");
                            setSortDirection(sortField == "memberName" && sortDirection == "asc"
                                ? "desc"
                                : "asc"
                            );
                        }}>名前{sortField == "memberName" && (sortDirection == "asc" ? "↓" : "↑")}</a>
					</th>
					<th scope="col">
                    <a onClick = {() => {
                            setSortField("age");
                            setSortDirection(sortField == "age" && sortDirection == "asc"
                                ? "desc"
                                : "asc"
                            );
                        }}>年齢{sortField == "age" && (sortDirection == "asc" ? "↓" : "↑")}</a>
					</th>
					<th scope="col">
                    <a onClick = {() => {
                            setSortField("sexFlg");
                            setSortDirection(sortField == "sexFlg" && sortDirection == "asc"
                                ? "desc"
                                : "asc"
                            );
                        }}>性別{sortField == "sexFlg" && (sortDirection == "asc" ? "↓" : "↑")}</a>
					</th>
					<th scope="col">
                    <a onClick = {() => {
                            setSortField("address");
                            setSortDirection(sortField == "address" && sortDirection == "asc"
                                ? "desc"
                                : "asc"
                            );
                        }}>住所{sortField == "address" && (sortDirection == "asc" ? "↓" : "↑")}</a>
					</th>
					<th scope="col">
                    <a onClick = {() => {
                            setSortField("telephone");
                            setSortDirection(sortField == "telephone" && sortDirection == "asc"
                                ? "desc"
                                : "asc"
                            );
                        }}>電話番号{sortField == "telephone" && (sortDirection == "asc" ? "↓" : "↑")}</a>
					</th>
					<th scope="col">
                    <a onClick = {() => {
                            setSortField("mail");
                            setSortDirection(sortField == "mail" && sortDirection == "asc"
                                ? "desc"
                                : "asc"
                            );
                        }}>メールアドレス{sortField == "mail" && (sortDirection == "asc" ? "↓" : "↑")}</a>
					</th>
					<th scope="col">
                    <a onClick = {() => {
                            setSortField("positionId");
                            setSortDirection(sortField == "positionId" && sortDirection == "asc"
                                ? "desc"
                                : "asc"
                            );
                        }}>役職{sortField == "positionId" && (sortDirection == "asc" ? "↓" : "↑")}</a>
					</th>
					<th scope="col">
                    <a onClick = {() => {
                            setSortField("placeId");
                            setSortDirection(sortField == "placeId" && sortDirection == "asc"
                                ? "desc"
                                : "asc"
                            );
                        }}>事業所{sortField == "placeId" && (sortDirection == "asc" ? "↓" : "↑")}</a>
					</th>
					<th scope="col">詳細</th>
					<th scope="col">更新</th>
					<th scope="col">削除</th>
				</thead>
				<tbody>
                    {members.map(member => (
                        <tr key={member.memberId}>
                            <td>{member.memberId}</td>
                            <td>{member.memberName}</td>
                            <td>{member.age}</td>
                            <td>{member.sexFlg == 0 ? "男" : "女"}</td>
                            <td>{member.address}</td>
                            <td>{member.telephone}</td>
                            <td>{member.mail}</td>
                            <td>{positions.find(position => position.id == member.positionId) ?.positionName}</td>
                            <td>{places.find(place => place.id == member.placeId) ?.placeName}</td>
                            <td>
                                <button onClick = {handleDetail}>詳細</button>
                            </td>
                            <td>
                                <button onClick = {handleUpdate}>更新</button>
                            </td>
                            <td>
                                <button onClick = {handleDelete}>削除</button>
                            </td>
                        </tr>
                    ))}
				</tbody>
			</table>
		</div>
    )
}

export default List;