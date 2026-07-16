import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import MenuLinks from "./MenuLinks";

function Detail() {

    // メンバー、事業所、役職を定義
    const [member, setMember] = useState([]);
    const [places, setPlaces] = useState([]);
    const [positions, setPositions] = useState([]);

    // urlからidを取得する
    const { id } = useParams();

    // 起動後すぐに実行
    useEffect(() => {
        fetch(`http://localhost:8080/members/detail/${id}`)
            .then(response => response.json())
            .then(data => {
                setMember(data.member);
                setPlaces(data.places);
                setPositions(data.positions);
            })
    }, [id]);

    // 画面表示
    return(
        <>
            <div id="wrapper">
                <MenuLinks />
                <div className="menu">
                    <h2>詳細画面</h2>
                </div>
                <form>
                    <table className="ct">
                        <tr>
                            <th>社員ID</th>
                            <td>{ member.memberId }</td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td>{ member.memberName }</td>
                        </tr>
                        <tr>
                            <th>年齢</th>
                            <td>{ member.age }</td>
                        </tr>
                        <tr>
                            <th>性別</th>
                            <td>{ member.sexFlg === 0 ? "男" : "女" }</td>
                        </tr>
                        <tr>
                            <th>住所</th>
                            <td>{ member.address }</td>
                        </tr>
                        <tr>
                            <th>電話番号</th>
                            <td>{ member.telephone }</td>
                        </tr>
                        <tr>
                            <th>メールアドレス</th>
                            <td>{ member.mail }</td>
                        </tr>
                        <tr>
                            <th>役職</th>
                            <td>{ positions.find(position => position.id == member.positionId) ?.positionName }</td>
                        </tr>
                        <tr>
                            <th>事業所</th>
                            <td>{ places.find(place => place.id == member.placeId) ?.placeName }</td>
                        </tr>
                    </table>
                </form>
            </div>
        </>
    )
}

export default Detail;