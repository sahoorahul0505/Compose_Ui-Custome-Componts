# Jetpack Compose UI Components 🎨

A collection of custom Jetpack Compose UI components with smooth animations and effects.  
This repo demonstrates how to go beyond the defaults of Compose with creative animations 
and custom drawing.

## 📌 Components

### 1. Shadow Button
A custom button with a soft shadow, since Compose’s default shadow is limited.  
**Features:**
- Custom shadow drawn with `drawBehind` and `Paint`  
- Configurable blur, offset, and corner radius  
- Material3 Button with elevation set to 0.dp for only the custom shadow  

📽️ Demo:  
![Shadow Button](demo/shadow_button.gif)

---

### 2. Expandable Card
A card that expands/collapses smoothly with blur and size animations.  
**Features:**
- Expand/collapse with `animateContentSize`  
- Temporary blur effect with `animateDpAsState`  
- Press feedback before expansion  

📽️ Demo:  
![Expandable Card](demo/expandable_card.gif)

---

### 3. Flippable Card
A card that flips with 3D rotation, color transition, and text/emoji animation.  
**Features:**
- 3D rotation with `graphicsLayer`  
- Color animation with `animateColorAsState`  
- Text fade and rotation with easing  
- Click to flip and reveal hidden content  

📽️ Demo:  
![Flippable Card](demo/flippable_card.gif)

---

### 4. Card Slide Animation
A horizontal card slider with background crossfade and smooth focus-based animations.  
**Features:**
- Background image transition with `Crossfade`  
- Card scaling with `animateFloatAsState`  
- Grayscale ↔ Color animation using `ColorMatrix`  
- Text letter-spacing animation for active card  
- Built with only `Crossfade` and `HorizontalPager`  

📽️ Demo:  
![Card Slide Animation](demo/card_slide_animation.gif)

---

## 🚀 How to Run
1. Clone the repository  
   ```bash
   git clone https://github.com/sahoorahul0505/Compose_Ui-Custome-Componts.git
