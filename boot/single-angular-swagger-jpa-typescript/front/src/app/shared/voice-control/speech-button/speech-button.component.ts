import {
  Component,
  OnInit,
  ViewChild,
  TemplateRef,
  OnDestroy
} from '@angular/core';
import {
  VoiceControlService
} from "@app/core/services/voice/voice-control.service";
import {
  VoiceRecognitionService
} from "@app/core/services/voice/voice-recognition.service";

import {
  BsModalService
} from 'ngx-bootstrap/modal';
import {
  BsModalRef
} from 'ngx-bootstrap/modal/bs-modal-ref.service';


@Component({
  selector: 'sa-speech-button',
  templateUrl: './speech-button.component.html',
  styles: [`.vc-title-error {display: block;}`]
})
export class SpeechButtonComponent implements OnInit, OnDestroy {


  @ViewChild('speechPopover') speechPopover;
  @ViewChild('helpTemplate') helpTemplate;

  public hasError: boolean = false;
  public enabled: boolean = false;
  public isToggled: boolean = false;

  modalRef: BsModalRef;

  private subs = {
    speech: null,
    help: null
  }

  constructor(
    private modalService: BsModalService,
    private voiceControlService: VoiceControlService,
    private voiceRecognitionService: VoiceRecognitionService
  ) {
    this.enabled = this.voiceControlService.state.enabled && this.voiceControlService.state.available;

    this.subs.speech = this.voiceControlService.speechEvent$.subscribe((event) => {
      this.respondTo(event)
    });

    this.subs.help = this.voiceControlService.showHelp$.subscribe((value) => {
      if(value){
        this.openHelpModal()
      } else {
        this.closeHelpModal()

      }
    })
  }

  ngOnInit(): void {}
  ngOnDestroy(): void {
    this.subs.speech && this.subs.speech.unsubscribe()
    this.subs.help && this.subs.help.unsubscribe()
  }


  openHelpModal() {
    this.modalRef = this.modalService.show(this.helpTemplate);
  }
  closeHelpModal(){
    this.modalRef && this.modalRef.hide()
  }


  toggleVoiceControl() {
    this.isToggled = !this.isToggled;

    if (!this.voiceControlService.state.started) {
      this.voiceControlService.voiceControlOn();
    } else {
      this.voiceControlService.voiceControlOff();
    }

    if(!this.isToggled){
      setTimeout(()=>{
        this.speechPopover.hide()
      }, 10)
    }
  }

  private respondTo(event) {
    if(!event) return
    switch (event.type) {
      case 'start':
        this.hasError = false;
        break;
      case 'error':
        this.hasError = true;
        break;
      case 'match':
      case 'no match':
        if (this.isToggled) {
          this.speechPopover.hide();
        }
        break
    }

  }
}
