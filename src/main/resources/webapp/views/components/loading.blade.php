@props(["target" => ""])

<div wire:loading class="loading-wrap" @if($target) wire:target="{{ $target }}" @endif >
    <div class="loader"><div></div><div></div><div></div></div>
</div>
