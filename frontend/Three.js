<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8"/>
<meta content="width=device-width,initial-scale=1.0" name="viewport"/>
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    html, body {
      width: 100%;
      height: 100%;
      overflow: hidden;
      background: #000;
    }
  </style>
</head>
<body>
<!-- STITCH_THREEJS_START:ANIMATION_46 class="fixed inset-0 w-full h-full bg-transparent" -->
<div class="fixed inset-0 w-full h-full bg-transparent" style="display:block;">
<script src="https://ajax.googleapis.com/ajax/libs/threejs/r125/three.min.js"></script>
<div id="threejs-container-ANIMATION_46" style="width:100%;height:100%"></div>
<script>
(function() {
  const container = document.getElementById('threejs-container-ANIMATION_46');
  const devicePixelRatio = window.devicePixelRatio || 1;
  const width = container.clientWidth || window.innerWidth;
const height = container.clientHeight || window.innerHeight;

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(75, width / height, 0.1, 1000);
const renderer = new THREE.WebGLRenderer({ alpha: true, antialias: true });
renderer.setSize(width, height);
renderer.setPixelRatio(window.devicePixelRatio);
container.appendChild(renderer.domElement);

// Create a group to hold our "Medical Shield/Cross" representation
const medicalGroup = new THREE.Group();
scene.add(medicalGroup);

// Create a stylized 3D Cross (PhilHealth branding symbol)
const material = new THREE.MeshPhongMaterial({ 
    color: 0x008d41, 
    shininess: 100,
    specular: 0x111111
});

const barSize = 1.5;
const barThickness = 0.5;

const horizontalBar = new THREE.BoxGeometry(barSize, barThickness, barThickness);
const horizontalMesh = new THREE.Mesh(horizontalBar, material);
medicalGroup.add(horizontalMesh);

const verticalBar = new THREE.BoxGeometry(barThickness, barSize, barThickness);
const verticalMesh = new THREE.Mesh(verticalBar, material);
medicalGroup.add(verticalMesh);

// Add a surrounding ring/shield
const ringGeo = new THREE.TorusGeometry(1.2, 0.05, 16, 100);
const ringMat = new THREE.MeshPhongMaterial({ color: 0xf9d423 });
const ring = new THREE.Mesh(ringGeo, ringMat);
medicalGroup.add(ring);

const ambientLight = new THREE.AmbientLight(0xffffff, 0.7);
scene.add(ambientLight);

const pointLight = new THREE.PointLight(0xffffff, 1);
pointLight.position.set(5, 5, 5);
scene.add(pointLight);

camera.position.z = 5;

// Animation loop
let mouseX = 0;
let mouseY = 0;

window.addEventListener('mousemove', (event) => {
    mouseX = (event.clientX / window.innerWidth) * 2 - 1;
    mouseY = -(event.clientY / window.innerHeight) * 2 + 1;
});

function animate() {
    requestAnimationFrame(animate);
    
    // Smooth follow rotation like MetaMask fox
    medicalGroup.rotation.y += (mouseX * 0.5 - medicalGroup.rotation.y) * 0.05;
    medicalGroup.rotation.x += (-mouseY * 0.5 - medicalGroup.rotation.x) * 0.05;
    
    // Subtle floating
    medicalGroup.position.y = Math.sin(Date.now() * 0.001) * 0.1;
    
    renderer.render(scene, camera);
}

window.addEventListener('resize', () => {
    const newWidth = container.clientWidth || window.innerWidth;
    const newHeight = container.clientHeight || window.innerHeight;
    camera.aspect = newWidth / newHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(newWidth, newHeight);
});

animate();
})();
</script>
</div>
<!-- STITCH_THREEJS_END:ANIMATION_46 -->
</body>
</html>
