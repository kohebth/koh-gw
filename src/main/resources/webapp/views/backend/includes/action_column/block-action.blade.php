@can("edit_{str_replace('-', '_', $moduleName)}")
    @if(!$item->isBlock())
        <button wire:click="updateMultiple('block', '{{ $item->id }}')"
                class="btn btn-warning btn-sm mb-1" data-toggle="tooltip" title="{{__("Block")}}">
            <i class="fas fa-ban"></i>
        </button>
    @endif
@endcan
