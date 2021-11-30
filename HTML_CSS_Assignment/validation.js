
// const tbodyEl = document.getElementById("tablebody");

function nameCheck(str) {
    var regex = /^[a-zA-Z]+ [a-zA-Z]*$/;
    var regex1 = /^[a-zA-Z]*$/;
    if (regex.test(str) || regex1.test(str)) {
        return true;
    }
    return false;
}
function emailcheck1(str) {
    var at = "@"
    var dot = "."
    var lat = str.indexOf(at)
    var lstr = str.length
    var ldot = str.indexOf(dot)
    if (str.indexOf(at) == -1) {
        return false;
    }

    if (str.indexOf(at) == -1 || str.indexOf(at) == 0 || str.indexOf(at) == lstr) {
        return false;
    }

    if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0 || str.indexOf(dot) == lstr) {
        return false;
    }

    if (str.indexOf(at, (lat + 1)) != -1) {
        return false;
    }
    
    if (str.substring(lat - 1, lat) == dot || str.substring(lat + 1, lat + 2) == dot) {
        return false;
    }

    if (str.indexOf(dot, (lat + 2)) == -1) {
        return false;
    }

    if (str.indexOf(" ") != -1) {
        return false;
    }
    return true;
}
function isURL(str) {
    var expression = /(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})/gi;
    var regex = new RegExp(expression);

    if (str.match(regex)) {
        return true;
    } else {
        return false;
    }
}

function isImgLink(url) {
    if(typeof url !== 'string') return false;
    return(url.match(/^http[^\?]*.(jpg|jpeg|gif|png|tiff|bmp)(\?(.*))?$/gmi) != null);
}

function validation() {
    var flag = 0;
    if (document.getElementById('name').value == "") {
        document.getElementById('errname').innerHTML = "*Name field is empty.";
        flag = 1;
        document.getElementById('errname').style.display = "block";
    }
    else if (nameCheck(document.getElementById('name').value) == false) {
        document.getElementById('errname').innerHTML = "*Enter Valid name";
        flag = 1;
        document.getElementById('errname').style.display = "block";
    }
    else {
        document.getElementById('errname').style.display = "none";
    }
    if (document.getElementById('email').value == "") {
        document.getElementById('erremail').innerHTML = "*Email field is empty.";
        flag = 1;
        document.getElementById('erremail').style.display = "block";
    }
    else if (emailcheck1(document.getElementById('email').value) == false) {
        document.getElementById('erremail').innerHTML = "*Enter Valid Email id.";
        flag = 1;
        document.getElementById('erremail').style.display = "block";
    }
    else {
        document.getElementById('erremail').style.display = "none";
    }
    if (document.getElementById('website').value == "") {
        document.getElementById('errwlink').innerHTML = "*website field is empty.";
        flag = 1;
        document.getElementById('errwlink').style.display = "block";

    }

    
    else if (isURL(document.getElementById('website').value) == false) {
        
        document.getElementById('errwlink').innerHTML = "*Enter Valid website URL";
        flag = 1;
        document.getElementById('errwlink').style.display = "block";

    }
    else {
        document.getElementById('errwlink').style.display = "none";

    }
    
    

    

    
    if (document.getElementById('imagelink').value == "") {
        document.getElementById('errilink').innerHTML = "*imagelink field is empty.";
        flag = 1;
        document.getElementById('errilink').style.display = "block";

    }
    else if (isImgLink(document.getElementById('imagelink').value) == false) {
        document.getElementById('errwlink').innerHTML = "*Enter Valid image link";
        flag = 1;
        document.getElementById('errwlink').style.display = "block";

    }
    else {
        document.getElementById('errilink').style.display = "none";
    }

    

    
    if (flag == 1) {
        return false;
    }
    return true;
}

function takevalue(e) {    
    if (validation() == true) {
        var name = document.getElementById('name').value;
        var email = document.getElementById('email').value;

        var str = document.getElementById('website').value;


        if (!$("input[type=radio]:checked").length > 0) {
            alert("Please select the gender");
            return false;
        }

        if (!$('input[type=checkbox]:checked').length > 0) {
            alert("Please select atleast one of the skills");
            return false;
        }

        
        var m = document.getElementById('male');
        var f = document.getElementById('female');

        if (m.checked == true)
            var gender = "MALE";
        else if (f.checked == true)
            var gender = "FEMALE";
        else {
            document.getElementById('errgend').style.display = "none";
            var gender = "can't say";
        }
        var items = document.getElementsByName('skills');
        var selectedItems = "";
        for (var i = 0; i < items.length; i++) {
            if (items[i].type == 'checkbox' && items[i].checked == true)
                selectedItems += items[i].value + ",";
        }
        var newSelectedItems=selectedItems.substring(0,selectedItems.length-1);
        var imagelink = document.getElementById('imagelink').value;
       
        
        document.getElementById("tablebody").innerHTML += `
    <tr>
        <td class="new">
          <b>${name}</b><br>
        ${gender}<br>
        ${email}<br>
        <u><a href="${str}" target="_blank">${str}</a></u><br>
        ${newSelectedItems}
        </td>

    <td><img src="${imagelink}" alt="" border style = "3px solid yellowgreen" height="120" width="130" />
    </td>
    </tr>
`;

document.getElementById("Enrollment-form").reset();
    }
    else {
        return false;
    }

}
