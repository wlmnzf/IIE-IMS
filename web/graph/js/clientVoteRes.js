$(document).ready(function(){
    var json=$("#cnt").val();
    // console.log(json);
    var jsonObj=JSON.parse(json);
    // console.log(jsonObj);
    var res=jsonObj["res"];
    var form=JSON.parse(jsonObj["form"]);
    var title=jsonObj["title"];
    var total=jsonObj["total"];
    console.log(res);
    console.log(form);
    console.log(title);
    console.log(total);


    $(".form-title").html(title);



     for(var i=0;i<form.length;i++)
     {
         var data=form[i]["data"];
         var title=form[i][0];
         var cuRes=res[i];
         cuRes=cuRes.split('|');

         if(form[i]["type"]=="t3")
         {
             var index=0;
             var tt="<p><h4>\"+title+\"</h4></p>";
             $(".inform-form").append(tt);

             for(var cukey in data[1])
             {

                 var node="><div class=\"vote-result\">" +
                     " <div class=\"vote-choice\">"+cukey+"</div>" +
                     " <div class=\"progress vote-progress\">" +
                     " <div class=\"progress-bar progress-bar-info\" role=\"progressbar\" aria-valuenow=\""+cuRes[index++]+"\" aria-valuemin=\"0\" aria-valuemax=\""+total+"\" style=\"width: 20%\">" +
                     " </div>" +
                     " </div>" +
                     "</div>";
                 $(".inform-form").append(node);
             }

         }
         else
         {
             var index=0;
             for(var cukey in data[1])
             {
                 var tt="<p><h4>\"+title+\"</h4></p>";
                 $(".inform-form").append(tt);
                 var node="<div class=\"vote-result\">" +
                     " <div class=\"vote-choice\">"+data[1][cukey]+"</div>" +
                     " <div class=\"progress vote-progress\">" +
                     " <div class=\"progress-bar progress-bar-info\" role=\"progressbar\" aria-valuenow=\""+cuRes[index++]+"\" aria-valuemin=\"0\" aria-valuemax=\""+total+"\" style=\"width: 20%\">" +
                     " </div>" +
                     " </div>" +
                     "</div>";
                 $(".inform-form").append(node);
             }
         }
     }

})