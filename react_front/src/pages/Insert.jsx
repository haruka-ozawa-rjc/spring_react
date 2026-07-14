import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";

function Insert() {

    // ページ遷移の関数を生成
    const navigate = useNavigate();

    // 事業所、役職情報を受け取る
    const [places, setPlaces] = useState([]);
    const [positions, setPositions] = useState([]);

    // バリデーションの関数を定義
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm();

    // 起動後すぐに実行
    useEffect(() => {

        // 新規登録に必要な情報を受け取る
        fetch(`http://localhost:8080/members/insert`)
            .then(response => response.json())
            .then(data =>{
                setPlaces(data.places);
                setPositions(data.positions);
            });
    }, []);

    // メニュー押して遷移
    const handleMenu = () => {
        navigate('/');
    }

    // 送信ボタン押して、情報を確認画面に送り遷移
    const onSubmit = (data) => {

        // 選択した事業所、役職を定義
        const selectedPosition = positions.find(
            position => position.id == data.positionId
        );
        const selectedPlace = places.find(
            place => place.id == data.placeId
        );

        // 確認画面に遷移、
        navigate('/insertConf', {
            state: {
                ...data,
                positionName: selectedPosition?.positionName,
                placeName: selectedPlace?.placeName
            }
        });
    }

    return (
        <>
             <div id="wrapper">
                <div className="menu">
                    <a onClick={ handleMenu }>メニュー</a><br />
                </div>
                <div className="menu">
                    <h2>新規登録画面</h2>
                </div>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <table className="ct">
                        <tr>
                            <th>社員ID</th>
                            <td>
                                <input type="text"
                                    {...register("memberId", {
                                       required: "社員IDは必須です。",
                                       maxLength: {
                                        value: 10, 
                                        message: "10文字以内で入力してください。",
                                       },
                                    })}
                                     />
                                {errors.memberId && (
                                    <div className="error-message">{errors.memberId.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td>
                                <input type="text"
                                    {...register("memberName", { 
                                       required: "氏名は必須です。", 
                                       maxLength: {
                                        value: 40,
                                        message: "40文字以内で入力してください。" ,
                                       },
                                    })} />
                                {errors.memberName && (
                                    <div className="error-message">{errors.memberName.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>年齢</th>
                            <td>
                                <input type="text"
                                    {...register("age", {
                                        required: "年齢は必須です。",
                                        maxLength: {
                                            value: 3,
                                            message: "3桁以内で入力してください",
                                        },
                                    })} />
                                {errors.age && (
                                    <div className="error-message">{errors.age.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>性別</th>
                            <td>
                                男<input 
                                    type="radio" 
                                    value= "0" 
                                    {...register("sexFlg", {
                                        required: "性別は必須です。",
                                    })}/>
                                女<input 
                                    type="radio" 
                                    value="1" 
                                    {...register("sexFlg", {
                                        required: "性別は必須です。",
                                    })} />
                                {errors.sexFlg && (
                                    <div className="error-message">{errors.sexFlg.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>住所</th>
                            <td>
                                <input type="text"
                                    {...register("address", {
                                        maxLength: {
                                            value: 50, 
                                            message: "50文字以内で入力してください",
                                    },
                                })} />
                                {errors.address && (
                                    <div className="error-message">{errors.address.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>電話番号</th>
                            <td>
                                <input type="text"
                                     {...register("telephone", {
                                        maxLength: {
                                            value: 11,
                                            message: "11文字以内で入力してください"
                                       },
                                    })}  />
                                {errors.telephone && (
                                    <div className="error-message">{errors.telephone.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>メールアドレス</th>
                            <td>
                                <input type="text"
                                       {...register("mail", {
                                        maxLength: {
                                            value: 20,
                                            message: "20文字以内で入力してください"
                                       },
                                    })}  />
                                {errors.mail && (
                                    <div className="error-message">{errors.mail.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>役職</th>
                            <td>
                                <select {...register("placeId")} >
                                    <option value="">選択してください</option>
                                    {positions.map((position) => (
                                        <option key={ position.id } value={ position.id } {...register("positionId")} >
                                            { position.positionName }
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>事業所</th>
                            <td>
                                <select {...register("placeId")} >
                                    <option value="">選択してください</option>
                                    { places.map((place) => (
                                        <option key={ place.id } value={ place.id }>
                                            { place.placeName }
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr>
                    </table>
                    <div className="menu">
                        <input type="submit" value="登録"/>
                    </div>
                </form>
            </div>
        </>
    )   
}

export default Insert;