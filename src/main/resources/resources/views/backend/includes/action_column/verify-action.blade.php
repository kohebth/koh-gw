@can("edit_{str_replace('-', '_', $moduleName)}")
    @if(in_array('not_vefify', $item->getVerifyAction()))
        <button wire:click="updateMultiple('not_verify', '{{$item->id}}')"
                class="btn btn-warning btn-sm mb-1" data-toggle="tooltip" title="{{__("Not vefify")}}">
            <i class="fas fa-ban"></i>
        </button>
    @endif
    @if(in_array('verify', $item->getVerifyAction()))
        <button wire:click="updateMultiple('verify', '{{$item->id}}')"
                class="btn btn-success btn-sm mb-1" data-toggle="tooltip" title="{{__("Vefify")}}">
            <i class="fas fa-check"></i>
        </button>
    @endif
@endcan
