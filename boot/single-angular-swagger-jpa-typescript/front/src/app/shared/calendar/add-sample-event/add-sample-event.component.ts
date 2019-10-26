import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';


@Component({

  selector: 'sa-add-sample-event',
  templateUrl: './add-sample-event.component.html',
})
export class AddSampleEvent implements OnInit {

  public icons:Array<string> = [
    'fa-info',
    'fa-warning',
    'fa-check',
    'fa-user',
    'fa-lock',
    'fa-clock-o'
  ];
  public colorClassNames:Array<any> = [
    {
      bg: 'bg-color-darken',
      txt: 'txt-color-white'
    },
    {
      bg: 'bg-color-blue',
      txt: 'txt-color-white'
    },
    {
      bg: 'bg-color-orange',
      txt: 'txt-color-white'
    },
    {
      bg: 'bg-color-greenLight',
      txt: 'txt-color-white'
    },
    {
      bg: 'bg-color-blueLight',
      txt: 'txt-color-white'
    },
    {
      bg: 'bg-color-red',
      txt: 'txt-color-white'
    }
  ];

  public activeIcon:string;
  public activeColorClass:any;
  @Input() public title: string;
  @Input() public description: string;
  @Output() addSample = new EventEmitter();

  constructor() {}

  ngOnInit() {
    this.activeIcon = this.icons[0];
    this.activeColorClass = this.colorClassNames[0]
  }

  setIcon(icon:string) {
    this.activeIcon = icon
  }

  setColorClass(colorClassName) {
    this.activeColorClass = colorClassName
  }

  addEventSample() {
    if(!this.description || !this.title){
      return
    }
    let event = {
     title: this.title,
     description: this.description,
     className: this.activeColorClass.bg + ' ' + this.activeColorClass.txt,
     icon: this.activeIcon
    }

    this.addSample.emit(event)
    this.description = '';
    this.title = ''
  }
}
