import {sequence, trigger, stagger, animate, style, group, query, transition, keyframes, animateChild} from '@angular/animations';
// const query = (s,a,o={optional:true})=>q(s,a,o);

// export const routerTransition = trigger('routerTransition', [
//   transition('* => *', [

//   ])
// ]);


export function doTransition(fromState, toState) {
  return toState >= fromState;
}

export const routerTransition = trigger('routerTransition', [
  transition(doTransition, [
    // query(':enter, :leave', style({ position: 'fixed', width:'100%' }), { optional: true }),
    // group([  // block executes in parallel
    //   query(':enter', [
    //     style({ transform: 'translateX(100%)', zIndex: 9999 }),
    //     animate('0.5s ease-in-out', style({ transform: 'translateX(0%)' }))
    //   ], { optional: true })
    // ]),



    query(':enter, :leave', style({ position: 'absolute', width:'100%', height: '100%' }), {optional: true}),
    query(':enter', style({ transform: 'translateY(100%)' }), {optional: true}),
    sequence([
      query(':leave', animateChild(), {optional: true}),
      group([
        query(':leave', [
          style({ transform: 'translateY(0%)', opacity: '1'  }),
          animate('300ms cubic-bezier(.36,.13,.69,.98)',
            style({ transform: 'translateY(20%)' , opacity: '0'  }))
        ], {optional: true}),
        query(':enter', [
          style({ transform: 'translateY(20%)', opacity: '0' }),
          animate('300ms cubic-bezier(.36,.13,.69,.98)',
            style({ transform: 'translateY(0%)', opacity: '1'  })),
        ], {optional: true}),
      ]),
      query(':enter', animateChild(), {optional: true}),
    ])













  ])
]);
