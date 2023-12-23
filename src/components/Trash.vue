<!-- eslint-disable vue/multi-word-component-names -->
<!-- eslint-disable linebreak-style -->
<!-- eslint-disable eol-last -->
<template>
<div class="email-contents" id = "email-contents">
    <div class="new-message-label" style="margin-left: 0">
        <span>Email</span>
        <button ><i class="fa-solid fa-circle-xmark"></i></button>
    </div>
    <div class="from">
        <span>From:</span>
        <div id="from-field">Hossam Osama</div>
    </div>
    <div class="subject">
        <span>Subject:</span>
        <div id="subject-field">about something very important</div>
    </div>
    <div class="Message">
        <span>Message body: </span>
        <div id="body-field">
        </div>
    </div>
    <div class="Attachments">
        <span>Attachments: </span>
        <div id="attchments-field"></div>
    </div>
        <div class="time">
    <span>Time remained to delete</span>
        <div id="time-field">30 Dec 15:14:00</div>
    </div>

</div>
</template>
