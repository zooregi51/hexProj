<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>退社者退職処理キャンセル</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }
        table {
            width: 100%;
            margin-top: 20px;
        }
        td {
            padding: 10px;
        }
        select, input[type="date"], input[type="text"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 4px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
	    /* 저장 버튼 스타일 */
	    input[type="button"] {
	        background-color: #4CAF50;
	        color: white;
	        border: none;
	        padding: 12px 25px;
	        cursor: pointer;
	        border-radius: 25px;
	        font-size: 16px;
	        font-weight: bold;
	        transition: background-color 0.3s, transform 0.2s;
	        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
	    }
	
	    input[type="button"]:hover {
	        background-color: #45a049;
	        transform: scale(1.05); /* 버튼을 살짝 확대 */
	    }
	
	    input[type="button"]:active {
	        background-color: #3e8e41;
	        transform: scale(0.98); /* 클릭 시 살짝 축소 */
	    }
    </style>
</head>
<body>
    <h2>退社者退職処理キャンセル</h2>
    <form action="/retire/retireState.do" method="POST" id="retireStateForm">
        <input type="hidden" name="empNo" id="empNo" value="${param.empNo}"/>
        <table>
            <tr>
                <td>退職区分</td>
                <td>
                    <select id="retiredForm" name="retiredForm">
                        <option value="retirementage">定年退職</option>
                        <option value="redundancy">リストラ</option>
                        <option value="voluntaryretire">自発的退職</option>
                        <option value="retirementofexecutives">役員退職</option>
                        <option value="interimsettlement">中間精算</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>退職日</td>
                <td><input type="date" id="retiredDate" name="retiredDate"></td>
            </tr>
            <tr>
                <td>退職後の連絡先</td>
                <td><input type="text" id="retiredPhonenum" name="retiredPhonenum" placeholder="예: 010-1234-5678"></td>
            </tr>
        </table>
        <input type="button" onclick="javascript:fnSaveRetireState();"value="저장">
    </form>
    <script type="text/javascript">
        //퇴직처리정보 저장
        function fnSaveRetireState(){

            // 각 입력 필드의 값 가져오기
            const retiredForm = document.getElementById("retiredForm").value;
            const retiredDate = document.getElementById("retiredDate").value;
            const retiredPhonenum = document.getElementById("retiredPhonenum").value;

            // 항목 null 값 체크
            if (!retiredForm || !retiredDate || !retiredPhonenum) {
                alert("退社情報をすべて記入してください.");
                return;
            }
            
            //저장 실행
            if(confirm("本当に退職扱いにしますか")){
                document.getElementById("retireStateForm").submit();
            }
        }
    </script>
</body>
</html>